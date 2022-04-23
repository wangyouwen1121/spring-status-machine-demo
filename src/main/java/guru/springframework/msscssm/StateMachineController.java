package guru.springframework.msscssm;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscssm.domain.StateDetail;
import guru.springframework.msscssm.services.task.StateCommonService;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

    @Resource
    private StateCommonService stateMachineService;

    @RequestMapping("/taskChangeEntrance")
    public void changeTask(StateDetail stateDetail) throws Exception {
        stateMachineService.changeTask(stateDetail);

    }

    @RequestMapping("/jobChangeEntrance")
    public void changeJob(@RequestBody StateDetail stateDetail) throws Exception {

        stateMachineService.changeJob(stateDetail);

    }

}
