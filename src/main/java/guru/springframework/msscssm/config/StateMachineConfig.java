package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;


@Slf4j
@RequiredArgsConstructor
@EnableStateMachineFactory
@Configuration
public class StateMachineConfig extends StateMachineConfigurerAdapter<JobState, JobEvents> {

    private final Action<JobState, JobEvents> createJobAction;
    private final Action<JobState, JobEvents> stopJobAction;
    private final Action<JobState, JobEvents> startJobAction;

    @Override
    public void configure(StateMachineStateConfigurer<JobState, JobEvents> states) throws Exception {
        states.withStates()
                .initial(JobState.STOPPED)
                .states(EnumSet.allOf(JobState.class))
                .end(JobState.STARTED)
                .end(JobState.STOPPED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<JobState, JobEvents> transitions) throws Exception {
        transitions.withExternal().source(JobState.STOPPED).target(JobState.STOPPED).event(JobEvents.CREATE_JOB)
                    .action(createJobAction)
                .and()
                .withExternal().source(JobState.STOPPED).target(JobState.STARTED).event(JobEvents.START_JOB)
                    .action(startJobAction)
                .and()
                .withExternal().source(JobState.STARTED).target(JobState.STOPPED).event(JobEvents.STOP_JOB)
                    .action(stopJobAction);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<JobState, JobEvents> config) throws Exception {
        StateMachineListenerAdapter<JobState, JobEvents> adapter = new StateMachineListenerAdapter<JobState, JobEvents>(){
            @Override
            public void stateChanged(State<JobState, JobEvents> from, State<JobState, JobEvents> to) {
                log.info(String.format("stateChanged(from: %s, to: %s)", from, to));
            }
        };

        config.withConfiguration().listener(adapter);
    }

//    public Guard<PaymentState, PaymentEvent> paymentIdGuard(){
//        return context -> {
//            return context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
//        };
//    }

//    public Action<PaymentState, PaymentEvent> preAuthAction(){
//        return context -> {
//            System.out.println("PreAuth was called!!!");
//
//            if (new Random().nextInt(10) < 8) {
//                System.out.println("Pre Auth Approved");
//                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
//                    .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
//                    .build());
//
//            } else {
//                System.out.println("Per Auth Declined! No Credit!!!!!!");
//                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
//                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
//                        .build());
//            }
//        };
//    }

//    public Action<PaymentState, PaymentEvent> authAction(){
//        return context -> {
//            System.out.println("Auth was called!!!");
//
//            if (new Random().nextInt(10) < 8) {
//                System.out.println("Auth Approved");
//                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_APPROVED)
//                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
//                        .build());
//
//            } else {
//                System.out.println("Auth Declined! No Credit!!!!!!");
//                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_DECLINED)
//                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
//                        .build());
//            }
//        };
//    }
}
