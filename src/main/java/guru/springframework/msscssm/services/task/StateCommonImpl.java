package guru.springframework.msscssm.services.task;

import java.util.Objects;

import javax.annotation.Resource;

import guru.springframework.msscssm.repository.StatusTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import guru.springframework.msscssm.config.JobStateMachineConfigBuilder;
import guru.springframework.msscssm.domain.StateDetail;
import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.job.JobEvents;
import guru.springframework.msscssm.domain.job.JobState;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.domain.task.TaskState;

/**
 * @author youwen.wang
 * @date 2022/4/23 8:57 下午
 */
@Service
public class StateCommonImpl implements StateCommonService {

    @Autowired
    private JobStateMachineConfigBuilder jobStateMachineConfigBuilder;

    @Resource
    private StateMachineFactory<TaskState, TaskEvents> taskStateMachineFactory;

    @Resource
    @Qualifier("taskStateMachine")
    private StateMachinePersister<TaskState, TaskEvents, StatusPersist> taskPersister;

    @Resource
    @Qualifier("jobStateMachine")
    private StateMachinePersister<JobState, JobEvents, StatusPersist> jobPersister;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private StatusTable statusTable;

    @Override
    public void changeTask(StateDetail stateDetail) throws Exception {

        StateMachine<TaskState, TaskEvents> stateMachine = taskStateMachineFactory.getStateMachine();
        taskPersister.restore(stateMachine, stateDetail);
        Message<TaskEvents> taskEventsMessage = MessageBuilder
            .withPayload(Objects.requireNonNull(TaskEvents.getTaskStateByState(stateDetail.getEventName())))
            .setHeader("stateDetail", stateDetail).build();
        stateMachine.sendEvent(taskEventsMessage);
        StatusPersist statusPersist = statusTable.findById(stateDetail.getId());
        if(!Objects.isNull(statusPersist)){
            BeanUtils.copyProperties(statusPersist,stateDetail);
        }

        taskPersister.persist(stateMachine,stateDetail);
    }

    @Override
    public void changeJob(StateDetail stateDetail) throws Exception {

        StatusPersist rawStatusPersist = statusTable.findById(stateDetail.getId());

        if(!Objects.isNull(rawStatusPersist)){
            stateDetail.setId(rawStatusPersist.getId());
        }
        StateMachine<JobState, JobEvents> stateMachine = jobStateMachineConfigBuilder.build(beanFactory);
        jobPersister.restore(stateMachine, stateDetail);

        Message<JobEvents> jobEventsMessage =
            MessageBuilder.withPayload(Objects.requireNonNull(JobEvents.getJobStateByState(stateDetail.getEventName())))
                .setHeader("stateDetail", stateDetail).build();

        stateMachine.sendEvent(jobEventsMessage);

        jobPersister.persist(stateMachine,Objects.isNull(rawStatusPersist)?stateDetail:rawStatusPersist);
    }

}
