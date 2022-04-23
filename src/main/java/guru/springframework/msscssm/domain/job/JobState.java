package guru.springframework.msscssm.domain.job;


public enum JobState {

    /**
     * 初始状态
     */
    INIT("INIT","初始状态"),

    CREATED("CREATED","已创建"),
    /**
     * job status enum
     */
    STARTED("STARTED","开启状态")



    ,STOPPED("STOPPED","停止状态");

    private final String state;

    private final String name;

    JobState(String state, String name) {
        this.state = state;
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public static JobState getJobStateByState(String state) {
        for (JobState jobState : values()) {
            if (jobState.name.equals(state)) {
                return jobState;
            }
        }
        return null;

    }
}
