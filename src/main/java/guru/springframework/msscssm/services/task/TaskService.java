package guru.springframework.msscssm.services.task;


import guru.springframework.msscssm.domain.StatusPersist;

import java.util.List;

/**
 * @author youwen.wang
 * @date 2022/4/9 1:45 下午
 */
public interface TaskService {

    void cancelTasks(List<String> taskIds);

    void requestGetFirmwareInfo(List<String> taskIds,String stateId);

    void deviceReportVersion(List<String> taskIds,String stateId);

    void retryTask(List<String> taskIds);

    void onDeviceDelete(List<String> taskIds);

    void createTask(StatusPersist statusPersist);
}
