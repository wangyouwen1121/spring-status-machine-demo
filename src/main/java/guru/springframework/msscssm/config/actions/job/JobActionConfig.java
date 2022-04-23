package guru.springframework.msscssm.config.actions.job;

import guru.springframework.msscssm.domain.StateDetail;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.services.task.StateCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.EventHeader;
import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.WithStateMachine;

import guru.springframework.msscssm.services.job.JobService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author youwen.wang
 * @date 2022/4/13 1:13 下午
 */
@WithStateMachine(id = "jobMachine", name = "jobMachine")
@Slf4j
public class JobActionConfig {

    @Autowired
    private JobService jobService;

    @Autowired
    private StateCommonService stateMachineService;

    @OnStateEntry(source = "INIT", target = "CREATED")
    public void createJob(@EventHeader("stateDetail") StateDetail stateDetail) throws Exception {

        StateDetail taskStateDetail=new StateDetail();
        taskStateDetail.setId("taskId");
        taskStateDetail.setEntityId("taskId");
        taskStateDetail.setParentId(stateDetail.getId());
        taskStateDetail.setEventName(TaskEvents.CREATE_TASK.getEvent());
        taskStateDetail.setAssetId("assetId");
        stateMachineService.changeTask(taskStateDetail);

    }

    @OnStateEntry(source = "CREATED", target = "STARTED")
    public void startJob(@EventHeader("stateDetail") StateDetail stateDetail) {
        System.out.println("start job");
    }
//
//    @OnStateEntry(source = "STARTED", target = "STOPPED")
//    public void stopJob(@EventHeader("job_id") String jobId,@EventHeader("state_id") String stateId) {
//        System.out.println("param is：" + jobId);
//        jobService.stopJob(String.valueOf(jobId),stateId);
//    }
}
