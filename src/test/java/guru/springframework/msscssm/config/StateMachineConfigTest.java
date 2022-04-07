package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<JobState, JobEvents> factory;

    @Test
    void testNewStateMachine() {
        StateMachine<JobState, JobEvents> sm = factory.getStateMachine(UUID.randomUUID());

        sm.start();

        System.out.println(sm.getState().toString());

        sm.sendEvent(JobEvents.START_JOB);

        System.out.println(sm.getState().toString());

        sm.sendEvent(JobEvents.STOP_JOB);

        System.out.println(sm.getState().toString());


    }
}