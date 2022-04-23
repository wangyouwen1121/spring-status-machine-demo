package guru.springframework.msscssm.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.job.JobEvents;
import guru.springframework.msscssm.domain.job.JobState;
import io.micrometer.core.instrument.util.StringUtils;

@Component
public class JobRepositoryStateMachine implements StateMachinePersist<JobState, JobEvents, StatusPersist> {

    @Autowired
    private StatusTable statusTable;

    @Override
    public void write(StateMachineContext<JobState, JobEvents> context, StatusPersist contextObj) throws Exception {

        StatusPersist statusPersist = statusTable.findById(contextObj.getId());
        contextObj.setState(context.getState().getState());
        if (Objects.isNull(statusPersist)) {

            statusTable.insert(contextObj);
        } else {
            statusTable.update(contextObj);
        }
    }

    @Override
    public StateMachineContext<JobState, JobEvents> read(StatusPersist contextObj) throws Exception {
        StatusPersist statusPersist = statusTable.findById(contextObj.getId());

        if (Objects.isNull(statusPersist)||StringUtils.isEmpty(statusPersist.getState())) {
            return new DefaultStateMachineContext<>(JobState.INIT, null, null, null, null, "jobMachine");
        } else {
            return new DefaultStateMachineContext<>(JobState.getJobStateByState(statusPersist.getState()), null, null, null, null, "jobMachine");
        }
    }
}