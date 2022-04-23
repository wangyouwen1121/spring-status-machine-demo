package guru.springframework.msscssm.config.actions.task;

import java.util.Collections;
import java.util.Objects;

import guru.springframework.msscssm.domain.StateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.annotation.EventHeader;
import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.WithStateMachine;

import guru.springframework.msscssm.services.task.TaskService;
import lombok.extern.slf4j.Slf4j;

@WithStateMachine(name="taskMachine",id = "taskMachine")
@Slf4j
public class TaskActionConfig {

    @Autowired
    private TaskService stateService;

    @OnStateEntry(target = "FAILED")
    public void cancelTask(@EventHeader("stateDetail") StateDetail stateDetail){

       // stateService.cancelTasks(Collections.singletonList(Objects.requireNonNull(stateDetail.getId())));
    }

    @OnStateEntry(source = "CREATE", target = "PUBLISHED")
    public void requestGetFirmwareInfo(@EventHeader("stateDetail") StateDetail stateDetail) {
        //stateService.requestGetFirmwareInfo(Collections.singletonList(stateDetail.getId()),stateInfo.getStateId());
    }

    @OnStateEntry(source = "PUBLISHED", target = "UPGRADING")
    public void deviceReportVersion(@EventHeader("stateDetail") StateDetail stateDetail) {

        /*stateService.deviceReportVersion(
            Collections.singletonList(stateInfo.getTaskId()),stateInfo.getStateId());*/

    }

    /*@OnStateEntry(source = "UPGRADING", target = "FAILED")
    public void retryTask(@EventHeader("task_id") String taskId,@EventHeader("state_id") String stateId) {
        System.out.println("param is ：" + taskId);
        stateService.retryTask(
            Collections.singletonList(Objects.requireNonNull(taskId).toString()));
    }

    @OnStateEntry(source = "UPGRADING", target = "FAILED")
    public void onDeviceDelete(@EventHeader("task_id") String taskId,@EventHeader("state_id") String stateId) {
        System.out.println("param is ：" + taskId);
        stateService.onDeviceDelete(Collections.singletonList(Objects.requireNonNull(taskId)));
    };*/

}
