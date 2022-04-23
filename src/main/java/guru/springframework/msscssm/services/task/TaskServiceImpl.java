package guru.springframework.msscssm.services.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.msscssm.domain.StatusPersist;

/**
 * @author youwen.wang
 * @date 2022/4/9 1:45 下午
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private StateCommonService stateCommonService;

    @Override
    public void cancelTasks(List<String> taskIds) {

        /*long count = stateCommonService.updateStatus(taskIds, ImmutableList.of(TaskState.INIT.name(),TaskState.PUBLISHED.name(),TaskState.UPGRADING.name()),
            TaskState.FAILED.name(), "Cancelled by user");
        
        if (count > 0) {
        
            taskIds.forEach(taskId->{
        
                LogPersist logPersist = JobServiceImpl.buildLogPersist(UUID.randomUUID().toString(), taskId, TaskEvents.CANCEL_TASK.name(),
                        TaskState.INIT.name(), TaskState.FAILED.name(), "taskMachine", StatusType.TASK.name());
                stateCommonService.saveLogPersist(logPersist);
            });
        
        
        }*/
    }

    @Override
    public void requestGetFirmwareInfo(List<String> taskIds, String stateId) {

        // long count = stateCommonService.updateStatus(Collections.singletonList(stateId),
        // ImmutableList.of(TaskState.INIT.name()),
        // TaskState.PUBLISHED.name(), null);
        // if (count > 0) {
        // taskIds.forEach(taskId->{
        // LogPersist logPersist = JobServiceImpl.buildLogPersist(UUID.randomUUID().toString(), taskId,
        // TaskEvents.REQUEST_UPGRADE_FIRMWARE_INFO.name(),
        // TaskState.INIT.name(), TaskState.FAILED.name(), "taskMachine", StatusType.TASK.name());
        // stateCommonService.saveLogPersist(logPersist);
        // });
        // }
    }

    @Override
    public void deviceReportVersion(List<String> taskIds, String stateId) {
        // long count = stateCommonService.updateStatus(Collections.singletonList(stateId),
        // ImmutableList.of(TaskState.PUBLISHED.name()),
        // TaskState.UPGRADING.name(), null);
        // if (count > 0) {
        // taskIds.forEach(taskId->{
        //
        // LogPersist logPersist = JobServiceImpl.buildLogPersist(UUID.randomUUID().toString(), taskId,
        // TaskEvents.DEVICE_REPORT_VERSION.name(),
        // TaskState.INIT.name(), TaskState.FAILED.name(), "taskMachine", StatusType.TASK.name());
        // stateCommonService.saveLogPersist(logPersist);
        // });
        // }
    }

    @Override
    public void retryTask(List<String> taskIds) {
        // long count = stateCommonService.updateStatus(taskIds, ImmutableList.of(TaskState.FAILED.name()),
        // TaskState.SUCCEEDED.name(), null);
        // taskIds.forEach(taskId->{
        //
        // LogPersist logPersist = JobServiceImpl.buildLogPersist(UUID.randomUUID().toString(), taskId,
        // TaskEvents.RETRY_TASK.name(),
        // TaskState.INIT.name(), TaskState.FAILED.name(), "taskMachine", StatusType.TASK.name());
        // stateCommonService.saveLogPersist(logPersist);
        // });
    }

    @Override
    public void onDeviceDelete(List<String> taskIds) {
        // long count = stateCommonService.updateStatus(taskIds, ImmutableList.of(TaskState.UPGRADING.name()),
        // TaskState.FAILED.name(), null);
        // if (count > 0) {
        // taskIds.forEach(taskId->{
        //
        // LogPersist logPersist = JobServiceImpl.buildLogPersist(UUID.randomUUID().toString(), taskId,
        // TaskEvents.ON_DEVICE_DELETE.name(),
        // TaskState.INIT.name(), TaskState.FAILED.name(), "taskMachine", StatusType.TASK.name());
        // stateCommonService.saveLogPersist(logPersist);
        // });
        // }

    }

    @Override
    public void createTask(StatusPersist statusPersist) {

    }

}
