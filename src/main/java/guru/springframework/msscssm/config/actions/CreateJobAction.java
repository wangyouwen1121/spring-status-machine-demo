package guru.springframework.msscssm.config.actions;

import java.util.Random;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.services.JobServiceImpl;


@Component
public class CreateJobAction implements Action<JobState, JobEvents> {

    @Override
    public void execute(StateContext<JobState, JobEvents> context) {
        System.out.println("Auth was called!!!");

        if (new Random().nextInt(10) < 8) {
            System.out.println("Auth Approved");
            context.getStateMachine()
                .sendEvent(MessageBuilder.withPayload(JobEvents.START_JOB)
                    .setHeader(JobServiceImpl.JOB_ID_HEADER, context.getMessageHeader(JobServiceImpl.JOB_ID_HEADER))
                    .build());

        } else {
            System.out.println("Auth Declined! No Credit!!!!!!");
            context.getStateMachine()
                .sendEvent(MessageBuilder.withPayload(JobEvents.STOP_JOB)
                    .setHeader(JobServiceImpl.JOB_ID_HEADER, context.getMessageHeader(JobServiceImpl.JOB_ID_HEADER))
                    .build());
        }
    }
}
