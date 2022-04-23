package guru.springframework.msscssm.services.task;

import guru.springframework.msscssm.domain.StateDetail;

/**
 * @author youwen.wang
 * @date 2022/4/23 9:02 下午
 */
public interface StateCommonService {

    void changeTask(StateDetail stateDetail) throws Exception ;

    void changeJob(StateDetail stateDetail) throws Exception ;
}
