package guru.springframework.msscssm.config;

import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.job.JobEvents;
import guru.springframework.msscssm.domain.job.JobState;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.domain.task.TaskState;
import guru.springframework.msscssm.repository.JobRepositoryStateMachine;
import guru.springframework.msscssm.repository.TaskRepositoryStateMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

import java.util.EnumSet;


@Component
@EnableStateMachine(name="jobMachine")
@Slf4j
public class JobStateMachineConfigBuilder{

    private final static String MACHINEID = "jobMachine";


    @Autowired
    JobRepositoryStateMachine jobRepositoryStateMachine;


    @Bean(name="jobStateMachine")
    public StateMachinePersister<JobState, JobEvents, StatusPersist> stateMachinePersist(){
        return new DefaultStateMachinePersister<>(jobRepositoryStateMachine);
    }


    public StateMachine<JobState,JobEvents> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<JobState,JobEvents> builder = StateMachineBuilder.builder();

        System.out.println("build job status machine");

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .listener(jobListener())
                .beanFactory(beanFactory);

        builder.configureStates()
                .withStates()
                .initial(JobState.INIT)
                .states(EnumSet.allOf(JobState.class));

        builder.configureTransitions()
                .withExternal()
                .source(JobState.INIT)
                .target(JobState.CREATED)
                .event(JobEvents.CREATE_JOB)
                .and()
                .withExternal()
                .source(JobState.CREATED)
                .target(JobState.STARTED)
                .event(JobEvents.START_JOB)
                .and()
                .withExternal()
                .source(JobState.STARTED)
                .target(JobState.STOPPED)
                .event(JobEvents.STOP_JOB);
        return builder.build();
    }

    @Bean
    public Action<JobState, JobEvents> action() {
        return new Action<JobState, JobEvents>() {

            @Override
            public void execute(StateContext<JobState, JobEvents> context) {
                System.out.println(context);
            }
        };
    }

    @Bean
    public StateMachineListener<JobState,JobEvents> jobListener() {
        return new StateMachineListenerAdapter<JobState,JobEvents>() {
            @Override
            public void stateChanged(State from, State to) {
                if (from != null && to != null) {
                    log.info("job's state from [{}] change to [{}].", from.getId(), to.getId());
                }
            }
        };
    }
}
