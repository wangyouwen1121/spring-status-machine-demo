package guru.springframework.msscssm.config.guards;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.services.JobServiceImpl;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;


@Component
public class PaymentIdGuard implements Guard<JobState, JobEvents> {

    @Override
    public boolean evaluate(StateContext<JobState, JobEvents> context) {
        return context.getMessageHeader(JobServiceImpl.JOB_ID_HEADER) != null;
    }
}
