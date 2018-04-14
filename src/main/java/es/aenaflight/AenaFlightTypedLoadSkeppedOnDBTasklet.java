package es.aenaflight;

import es.aenaflight.repository.AenaFlightRawRepository;
import es.aenaflight.repository.AenaFlightTypedRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AenaFlightTypedLoadSkeppedOnDBTasklet implements Tasklet {

    @Value("${aena.flights.insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSideLimit:1000000}")
    private Long insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSideLimit;

    private AenaFlightTypedRepository aenaFlightTypedRepository;
    private AenaFlightRawRepository aenaFlightRawRepository;

    @Autowired
    public AenaFlightTypedLoadSkeppedOnDBTasklet(AenaFlightTypedRepository aenaFlightTypedRepository,
                                                 AenaFlightRawRepository aenaFlightRawRepository
    ) {
        this.aenaFlightTypedRepository = aenaFlightTypedRepository;
        this.aenaFlightRawRepository = aenaFlightRawRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        aenaFlightTypedRepository.insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSide(insertIntoAenaFlightTypedFromAenaFlightRawSkeppedOnDBSideLimit);

        if (aenaFlightRawRepository.isAenaFlightRawLoadSkippedExists()) {
            return RepeatStatus.CONTINUABLE;
        } else {
            return RepeatStatus.FINISHED;
        }
    }
}
