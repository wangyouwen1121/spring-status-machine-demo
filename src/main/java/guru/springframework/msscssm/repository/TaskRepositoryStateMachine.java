package guru.springframework.msscssm.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.domain.task.TaskState;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author youwen.wang
 * @date 2022/4/23 7:40 下午
 */

@Component
public class TaskRepositoryStateMachine implements StateMachinePersist<TaskState, TaskEvents, StatusPersist> {

    @Autowired
    private StatusTable statusTable;

    @Override
    public void write(StateMachineContext<TaskState, TaskEvents> context, StatusPersist contextObj) throws Exception {

        StatusPersist statusPersist = statusTable.findById(contextObj.getId());
        if (Objects.isNull(statusPersist)) {
            statusTable.insert(contextObj);
        } else {
            statusTable.update(contextObj);
        }
    }

    @Override
    public StateMachineContext<TaskState, TaskEvents> read(StatusPersist contextObj) throws Exception {
        StatusPersist statusPersist = statusTable.findById(contextObj.getId());
        if (Objects.isNull(statusPersist)||StringUtils.isEmpty(statusPersist.getState())) {
            return new DefaultStateMachineContext<>(TaskState.INIT, null, null, null, null, "jobMachine");
        } else {
            return new DefaultStateMachineContext<>(TaskState.getTaskStateByState(statusPersist.getState()), null, null, null, null,
                "taskMachine");
        }
    }
}
