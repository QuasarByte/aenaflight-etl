package es.aenaflight;

import es.aenaflight.repository.AenaFlightRawRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadSkippedListener extends StepExecutionListenerSupport {

    @Autowired
    private AenaFlightRawRepository aenaFlightRawRepository;

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        if (aenaFlightRawRepository.isAenaFlightRawLoadSkippedExists()) {
            return new ExitStatus("CONTINUE");
        } else {
            return new ExitStatus("FINISHED");
        }
    }

}
