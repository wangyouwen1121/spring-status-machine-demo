//package guru.springframework.msscssm.config;
//
//import java.util.EnumSet;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.statemachine.StateContext;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.action.Action;
//import org.springframework.statemachine.config.EnableStateMachine;
//import org.springframework.statemachine.config.StateMachineBuilder;
//import org.springframework.statemachine.config.StateMachineFactory;
//import org.springframework.statemachine.listener.StateMachineListener;
//import org.springframework.statemachine.listener.StateMachineListenerAdapter;
//import org.springframework.statemachine.state.State;
//import org.springframework.stereotype.Component;
//
//import guru.springframework.msscssm.domain.task.TaskEvents;
//import guru.springframework.msscssm.domain.task.TaskState;
//
///**
// * @author youwen.wang
// * @date 2022/4/8 5:00 下午
// */
//
//@Component
//@EnableStateMachine(name="taskMachine")
//@Slf4j
//public class TaskStateMachineConfigBuilder {
//
//    private final static String MACHINEID = "taskMachine";
//
//    public StateMachine<TaskState, TaskEvents> build() throws Exception {
//
//        StateMachineBuilder.Builder<TaskState, TaskEvents> builder = StateMachineBuilder.builder();
//
//        System.out.println("build task status machine");
//
//        builder.configureConfiguration()
//                .withConfiguration()
//                .listener(taskListener())
//                .machineId(MACHINEID);
//
//
//
//        builder.configureStates()
//                .withStates()
//                .initial(TaskState.INIT)
//                .states(EnumSet.allOf(TaskState.class));
//
//        builder.configureTransitions()
//                .withExternal()
//                .target(TaskState.FAILED)
//                .event(TaskEvents.CANCEL_TASK)
//                .and()
//                .withExternal()
//                .source(TaskState.PUBLISHED)
//                .target(TaskState.UPGRADING)
//                .event(TaskEvents.DEVICE_REPORT_VERSION)
//                .and()
//            .withExternal()
//                .source(TaskState.INIT)
//                .target(TaskState.PUBLISHED)
//                .event(TaskEvents.REQUEST_UPGRADE_FIRMWARE_INFO)
//                .and()
//                .withExternal()
//                .source(TaskState.FAILED)
//                .target(TaskState.SUCCEEDED)
//                .event(TaskEvents.RETRY_TASK)
//                .and()
//                .withExternal()
//                .source(TaskState.UPGRADING)
//                .target(TaskState.FAILED).event(TaskEvents.ON_DEVICE_DELETE);
//
//        return builder.build();
//    }
//
//    @Bean
//    public Action<TaskState, TaskEvents> taskAction() {
//        return new Action<TaskState, TaskEvents>() {
//
//            @Override
//            public void execute(StateContext<TaskState, TaskEvents> context) {
//                System.out.println(context);
//            }
//        };
//    }
//
//    @Bean
//    public StateMachineListener<TaskState, TaskEvents> taskListener() {
//        return new StateMachineListenerAdapter<TaskState, TaskEvents>() {
//            @Override
//            public void stateChanged(State from, State to) {
//                if (from != null && to != null) {
//                    log.info("task's state from [{}] change to [{}].", from.getId(), to.getId());
//                }
//            }
//        };
//    }
//
//}
