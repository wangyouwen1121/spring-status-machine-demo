package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.repository.JobTable;
import javafx.print.PrinterJob;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class JobStateChangeInterceptor extends StateMachineInterceptorAdapter<JobState, JobEvents> {

    private final JobTable jobTable;


    @Override
    public void preStateChange(State<JobState, JobEvents> state, Message<JobEvents> message,
                               Transition<JobState, JobEvents> transition, StateMachine<JobState, JobEvents> stateMachine) {

        Optional.ofNullable(message).flatMap(msg -> Optional.ofNullable(msg.getHeaders().getOrDefault(JobServiceImpl.JOB_ID_HEADER, -1L))).ifPresent(jobId -> {
            jobTable.updateStatus(String.valueOf(jobId), state.getId());
        });
    }
}
