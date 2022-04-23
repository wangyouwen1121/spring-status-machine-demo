package guru.springframework.msscssm.domain.task;

import guru.springframework.msscssm.domain.job.JobState;

/**
 * @author youwen.wang
 * @date 2022/4/7 10:19 下午
 */
public enum  TaskState {

    /**
     * task status
     */

    INIT("INIT","待推送"),

    CREATE("CREATE","已创建"),

    PUBLISHED("PUBLISHED","已推送"),

    UPGRADING("UPGRADING","进行中"),

    SUCCEEDED("SUCCEEDED","成功"),

    FAILED("FAILED","失败");

    private final String state;

    private final String name;

    TaskState(String state, String name) {
        this.state = state;
        this.name = name;
    }

    public String getState() {
        return state;
    }



    public static TaskState getTaskStateByState(String state) {
        for (TaskState taskState : values()) {
            if (taskState.name.equals(state)) {
                return taskState;
            }
        }
        return null;

    }

}
