package guru.springframework.msscssm.config.actions;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;


@Component
public class StopJobAction implements Action<JobState, JobEvents> {
    @Override
    public void execute(StateContext<JobState, JobEvents> context) {
        System.out.println("Sending Notification of Auth APPROVED");
    }
}
