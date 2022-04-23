package guru.springframework.msscssm.domain.job;


import guru.springframework.msscssm.domain.task.TaskEvents;

public enum JobEvents {

    /**
     * create Job
     */
    CREATE_JOB("CREATE_JOB","创建job"),

    /**
     * stop job
     */
    STOP_JOB("STOP_JOB","停止job"),

    /**
     * start job
     */
    START_JOB("START_JOB","开启job");

    private final String event;

    private final String name;

    JobEvents(String event, String name) {
        this.event = event;
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public String getName() {
        return name;
    }

    public static JobEvents getJobStateByState(String eventName) {
        for (JobEvents jobEvent : values()) {
            if (jobEvent.event.equals(eventName)) {
                return jobEvent;
            }
        }
        return null;

    }
}

