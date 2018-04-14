package es.aenaflight;

import es.aenaflight.repository.AenaFlightTypedRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AenaFlightTypedCleanTasklet implements Tasklet {

    private AenaFlightTypedRepository aenaFlightTypedRepository;

    @Autowired
    public AenaFlightTypedCleanTasklet(AenaFlightTypedRepository aenaFlightTypedRepository
    ) {
        this.aenaFlightTypedRepository = aenaFlightTypedRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        aenaFlightTypedRepository.truncateAenaFlightTyped();
        return RepeatStatus.FINISHED;
    }
}
