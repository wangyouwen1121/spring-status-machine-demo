package guru.springframework.msscssm.config;

import java.util.EnumSet;
import java.util.UUID;

import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.job.JobState;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.domain.task.TaskState;
import guru.springframework.msscssm.repository.TaskRepositoryStateMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;

import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableStateMachineFactory(name = "taskMachine")
public class TaskStateMachineConfig extends EnumStateMachineConfigurerAdapter<TaskState, TaskEvents> {


    @Autowired
    TaskRepositoryStateMachine taskRepositoryStateMachine;


    @Bean(name="taskStateMachine")
    public StateMachinePersister<TaskState, TaskEvents, StatusPersist> stateMachinePersist(){
        return new DefaultStateMachinePersister<>(taskRepositoryStateMachine);
    }


    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskState, TaskEvents> config)
            throws Exception {
        config.withConfiguration().autoStartup(true).listener(taskListener()).machineId("taskMachine");
    }


    @Bean
    public StateMachineListener<TaskState, TaskEvents> taskListener() {
        return new StateMachineListenerAdapter<TaskState, TaskEvents>() {
            @Override
            public void stateChanged(State from, State to) {
                if (from != null && to != null) {
                    log.info("task's state from [{}] change to [{}].", from.getId(), to.getId());
                }
            }
        };
    }


    @Override
    public void configure(StateMachineStateConfigurer<TaskState, TaskEvents> states)
            throws Exception {
        states.withStates()
                .initial(TaskState.INIT)
                .states(EnumSet.allOf(TaskState.class));}

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskState, TaskEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .target(TaskState.FAILED)
                .event(TaskEvents.CANCEL_TASK)
                .and()
                .withExternal()
                .source(TaskState.PUBLISHED)
                .target(TaskState.UPGRADING)
                .event(TaskEvents.DEVICE_REPORT_VERSION)
                .and()
                .withExternal()
                .source(TaskState.INIT)
                .target(TaskState.CREATE)
                .event(TaskEvents.CREATE_TASK)
                .and()
                .withExternal()
                .source(TaskState.CREATE)
                .target(TaskState.PUBLISHED)
                .event(TaskEvents.REQUEST_UPGRADE_FIRMWARE_INFO)
                .and()
                .withExternal()
                .source(TaskState.FAILED)
                .target(TaskState.SUCCEEDED)
                .event(TaskEvents.RETRY_TASK)
                .and()
                .withExternal()
                .source(TaskState.UPGRADING)
                .target(TaskState.FAILED).event(TaskEvents.ON_DEVICE_DELETE);
    }
}
