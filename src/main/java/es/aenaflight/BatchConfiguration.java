package es.aenaflight;

import es.aenaflight.repository.AenaFlightTypedRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:sql-postgresql.xml")
public class BatchConfiguration {

    @Value("${aena.flights.rawReaderPageSize:100}")
    private int aenaFlightRawReaderPageSize;

    @Value("${aena.flights.stepLoadAenaFlightRawToTypedSkippedChunkSize:100}")
    private int stepLoadAenaFlightRawToTypedSkippedChunkSize;

    @Value("${aena.flights.rawLoadSkippedReaderRaedLimit:100}")
    private int rawLoadSkippedReaderRaedLimit;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${aenaFlightRawPagedSelectClause}")
    private String aenaFlightRawPagedSelectClause;

    @Value("${aenaFlightRawPagedFromClause}")
    private String aenaFlightRawPagedFromClause;

    @Value("${aenaFlightRawPagedWhereClause}")
    private String aenaFlightRawPagedWhereClause;

    @Value("${insertIntoAenaflightTyped}")
    private String insertIntoAenaflightTyped;

    @Value("${aenaFlightRawLoadSkipped}")
    private String aenaFlightRawLoadSkipped;

    @Value("${stepLoadAenaFlightRawToTyped.taskExecutor.corePoolSize:10}")
    private Integer stepLoadAenaFlightRawToTypedTaskExecutorCorePoolSize;

    @Value("${stepLoadAenaFlightRawToTyped.taskExecutor.maxPoolSize:100}")
    private Integer stepLoadAenaFlightRawToTypedTaskExecutorMaxPoolSize;

    @Value("${stepLoadAenaFlightRawToTyped.taskExecutor.queueCapacity:50}")
    private Integer stepLoadAenaFlightRawToTypedTaskExecutorQueueCapacity;

    @Value("${stepLoadAenaFlightRawToTyped.taskExecutor.allowCoreThreadTimeOut:true}")
    private Boolean stepLoadAenaFlightRawToTypedTaskExecutorAllowCoreThreadTimeOut;

    @Value("${stepLoadAenaFlightRawToTyped.taskExecutor.keepAliveSeconds:1200}")
    private Integer stepLoadAenaFlightRawToTypedTaskExecutorKeepAliveSeconds;

    @Value("${stepLoadAenaFlightRawToTypedThrottleLimit:10}")
    private Integer stepLoadAenaFlightRawToTypedThrottleLimit;

    @Autowired
    private AenaFlightTypedRepository aenaFlightTypedRepository;

    @Bean
    public JdbcCursorItemReader aenaFlightRawLoadSkippedReader(DataSource dataSource) {

        Integer[] limit = {rawLoadSkippedReaderRaedLimit};

        return new JdbcCursorItemReaderBuilder<AenaFlightRaw>()
                .name("aenaFlightRawSkippedReader")
                .dataSource(dataSource)
                .sql(aenaFlightRawLoadSkipped)
                .queryArguments(limit)
                .rowMapper(new AenaFlightRawRowMapper())
                .build();
    }

    @Bean
    public JdbcPagingItemReader aenaFlightRawReader(DataSource dataSource) {

        Long typedMaxId = aenaFlightTypedRepository.getAenaFlightTypedMaxId();

        Map<String, Object> parameterValues = new HashMap<>();

        if (typedMaxId != null) {
            parameterValues.put("alreadyLoadedMaxId", typedMaxId);
        }

        return new JdbcPagingItemReaderBuilder<AenaFlightRaw>()
                .name("aenaFlightRawReader")
                .dataSource(dataSource)
                .queryProvider(pagingQueryProvider(dataSource, typedMaxId))
                .parameterValues(parameterValues)
                .rowMapper(new AenaFlightRawRowMapper())
                .pageSize(aenaFlightRawReaderPageSize)
                .build();
    }

    public PagingQueryProvider pagingQueryProvider(DataSource dataSource, Long typedMaxId) {
        SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();

        provider.setDataSource(dataSource);
        provider.setSelectClause(aenaFlightRawPagedSelectClause);
        provider.setFromClause(aenaFlightRawPagedFromClause);
        provider.setSortKey("id");

        if (typedMaxId != null) {
            provider.setWhereClause(aenaFlightRawPagedWhereClause);
        }

        try {
            return provider.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public AenaFlightRawToTypedProcessor aenaFlightRawToTypedProcessor() {
        return new AenaFlightRawToTypedProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<AenaFlightTyped> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AenaFlightTyped>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(insertIntoAenaflightTyped)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job etlJavaSolutionJob(JobCompletionNotificationListener listener,
                                       Step stepLoadAenaFlightRawToTyped,
                                       Step stepLoadAenaFlightRawToTypedSkipped) {
        return jobBuilderFactory.get("etlJavaSolutionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(stepLoadAenaFlightRawToTypedSkipped)
                .on("CONTINUE").to(stepLoadAenaFlightRawToTypedSkipped)
                .on("FINISHED").to(stepLoadAenaFlightRawToTyped)
                .end()
                .build();
    }

    @Bean
    public Job eltDBSolutionJob(JobCompletionNotificationListener listener,
                                AenaFlightTypedLoadSkeppedOnDBTasklet aenaFlightTypedLoadSkeppedOnDBTasklet,
                                AenaFlightTypedLoadDeltaOnDBTasklet aenaFlightTypedLoadDeltaOnDBTasklet,
                                AenaFlightDestinationDataCleanTasklet aenaFlightDestinationDataCleanTasklet,
                                AenaFlightDestinationDataAggregateTasklet aenaFlightDestinationDataAggregateTasklet
                                ) {
        return jobBuilderFactory.get("eltDBSolutionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(stepBuilderFactory.get("aenaFlightTypedLoadSkeppedOnDBTasklet").tasklet(aenaFlightTypedLoadSkeppedOnDBTasklet).build())
                .next(stepBuilderFactory.get("aenaFlightTypedLoadDeltaOnDBTasklet").tasklet(aenaFlightTypedLoadDeltaOnDBTasklet).build())
                .next(stepBuilderFactory.get("aenaFlightDestinationDataCleanTasklet").tasklet(aenaFlightDestinationDataCleanTasklet).build())
                .next(stepBuilderFactory.get("aenaFlightDestinationDataAggregateTasklet").tasklet(aenaFlightDestinationDataAggregateTasklet).build())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(stepLoadAenaFlightRawToTypedTaskExecutorCorePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(stepLoadAenaFlightRawToTypedTaskExecutorMaxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(stepLoadAenaFlightRawToTypedTaskExecutorQueueCapacity);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(stepLoadAenaFlightRawToTypedTaskExecutorAllowCoreThreadTimeOut);
        threadPoolTaskExecutor.setKeepAliveSeconds(stepLoadAenaFlightRawToTypedTaskExecutorKeepAliveSeconds);

        threadPoolTaskExecutor.setThreadGroupName("springBatchTaskExecutor");
        threadPoolTaskExecutor.setThreadNamePrefix("springBatchTaskExecutor-");

        return threadPoolTaskExecutor;
    }

    @Bean
    public Step stepLoadAenaFlightRawToTypedSkipped(JdbcCursorItemReader<AenaFlightRaw> aenaFlightRawLoadSkippedReader,
                                                    AenaFlightRawToTypedProcessor aenaFlightRawToTypedProcessor,
                                                    JdbcBatchItemWriter<AenaFlightTyped> writer,
                                                    LoadSkippedListener loadSkippedListener
    ) {
        return stepBuilderFactory.get("stepLoadAenaFlightRawToTypedSkipped")
                .<AenaFlightRaw, AenaFlightTyped>chunk(stepLoadAenaFlightRawToTypedSkippedChunkSize)
                .reader(aenaFlightRawLoadSkippedReader)
                .processor(aenaFlightRawToTypedProcessor)
                .writer(writer)
                .listener(loadSkippedListener)
                .build();
    }

    @Bean
    public Step stepLoadAenaFlightRawToTyped(JdbcPagingItemReader<AenaFlightRaw> itemReader, AenaFlightRawToTypedProcessor aenaFlightRawToTypedProcessor, JdbcBatchItemWriter<AenaFlightTyped> writer) {
        return stepBuilderFactory.get("stepLoadAenaFlightRawToTyped")
                .<AenaFlightRaw, AenaFlightTyped>chunk(stepLoadAenaFlightRawToTypedSkippedChunkSize)
                .reader(itemReader)
                .processor(aenaFlightRawToTypedProcessor)
                .writer(writer)
                .taskExecutor(taskExecutor())
                .throttleLimit(stepLoadAenaFlightRawToTypedThrottleLimit)
                .build();
    }
}