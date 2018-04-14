package es.aenaflight;

import es.aenaflight.repository.AenaFlightTypedRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AenaFlightTypedLoadDeltaOnDBTasklet implements Tasklet {

    @Value("${aena.flights.insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSideLimit:1000000}")
    private Long insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSideLimit;

    private AenaFlightTypedRepository aenaFlightTypedRepository;

    @Autowired
    public AenaFlightTypedLoadDeltaOnDBTasklet(AenaFlightTypedRepository aenaFlightTypedRepository
    ) {
        this.aenaFlightTypedRepository = aenaFlightTypedRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        aenaFlightTypedRepository.insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSide(insertIntoAenaFlightTypedFromAenaFlightRawDeltaOnDBSideLimit);

        if (aenaFlightTypedRepository.isAenaFlightRawAndTypedMaxAreEquals()) {
            return RepeatStatus.FINISHED;
        } else {
            return RepeatStatus.CONTINUABLE;
        }
    }
}
