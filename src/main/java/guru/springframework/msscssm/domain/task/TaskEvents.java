package guru.springframework.msscssm.domain.task;

import com.sun.source.util.TaskEvent;

/**
 * @author youwen.wang
 * @date 2022/4/7 10:19 下午
 */
public enum  TaskEvents {

    /**
     * create task
     */
    CREATE_TASK("CREATE_TASK","创建任务"),

    /**
     * cancel task
     */
    CANCEL_TASK("CANCEL_TASK","取消任务"),

    /**
     * retry task
     */
    RETRY_TASK("RETRY_TASK","重试任务"),

    /**
     * device delete
     */
    ON_DEVICE_DELETE("ON_DEVICE_DELETE","设备删除"),

    /**
     * device get firmware info
     */
    REQUEST_UPGRADE_FIRMWARE_INFO("REQUEST_UPGRADE_FIRMWARE_INFO","获取固件信息"),

    /**
     * device report version
     */
    DEVICE_REPORT_VERSION("DEVICE_REPORT_VERSION","设备上报版本");

    private final String event;

    private final String name;

    TaskEvents(String event, String name) {
        this.event = event;
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }


    public static TaskEvents getTaskStateByState(String eventName) {
        for (TaskEvents taskEvent : values()) {
            if (taskEvent.event.equals(eventName)) {
                return taskEvent;
            }
        }
        return null;

    }





}
