package es.aenaflight;

import es.aenaflight.repository.DestinationDataRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AenaFlightDestinationDataCleanTasklet implements Tasklet {

    private DestinationDataRepository destinationDataRepository;

    @Autowired
    public AenaFlightDestinationDataCleanTasklet(DestinationDataRepository destinationDataRepository) {
        this.destinationDataRepository = destinationDataRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        destinationDataRepository.truncateDestinationData();
        return RepeatStatus.FINISHED;
    }
}
