package guru.springframework.msscssm.config.actions;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.services.JobServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class StartJobAction implements Action<JobState, JobEvents>{

    @Override
    public void execute(StateContext<JobState, JobEvents> context) {
        System.out.println("start job call !!!");

        if (new Random().nextInt(10) < 8) {
            System.out.println("start job  Approved");
            context.getStateMachine().sendEvent(MessageBuilder.withPayload(JobEvents.START_JOB)
                    .setHeader(JobServiceImpl.JOB_ID_HEADER, context.getMessageHeader(JobServiceImpl.JOB_ID_HEADER))
                    .build());

        } else {
            System.out.println("can not start");
            context.getStateMachine().sendEvent(MessageBuilder.withPayload(JobEvents.STOP_JOB)
                    .setHeader(JobServiceImpl.JOB_ID_HEADER, context.getMessageHeader(JobServiceImpl.JOB_ID_HEADER))
                    .build());
        }
    }
}
