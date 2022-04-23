//package guru.springframework.msscssm.services.task;
//
//import java.util.Optional;
//
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.state.State;
//import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
//import org.springframework.statemachine.transition.Transition;
//import org.springframework.stereotype.Component;
//
//import guru.springframework.msscssm.domain.task.TaskEvents;
//import guru.springframework.msscssm.domain.task.TaskState;
//import lombok.RequiredArgsConstructor;
//
///**
// * @author youwen.wang
// * @date 2022/4/9 1:46 下午
// */
//@RequiredArgsConstructor
//@Component
//public class TaskStateChangeInterceptor extends StateMachineInterceptorAdapter<TaskState, TaskEvents> {
//
//    private final TaskTable taskTable;
//
//    @Override
//    public void preStateChange(State<TaskState, TaskEvents> state, Message<TaskEvents> message,
//        Transition<TaskState, TaskEvents> transition, StateMachine<TaskState, TaskEvents> stateMachine) {
//
//        Optional.ofNullable(message)
//            .flatMap(msg -> Optional.ofNullable(msg.getHeaders().getOrDefault(TaskServiceImpl.TASK_ID_HEADER, -1L)))
//            .ifPresent(jobId -> {
//                taskTable.updateStatus(String.valueOf(jobId), state.getId());
//            });
//    }
//
//}
