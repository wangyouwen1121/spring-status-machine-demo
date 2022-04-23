package guru.springframework.msscssm.services.job;

import java.util.*;

import com.google.common.collect.ImmutableList;
import guru.springframework.msscssm.domain.task.TaskEvents;
import guru.springframework.msscssm.services.task.StateCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.msscssm.domain.LogPersist;
import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.domain.StatusType;
import guru.springframework.msscssm.domain.job.JobEvents;
import guru.springframework.msscssm.domain.job.JobState;
import guru.springframework.msscssm.domain.task.TaskState;


/**
 * Created by jt on 2019-08-10.
 */

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private StateCommonService stateCommonService;


    @Override
    public String createJob(String entityId,String stateId) {

//        StatusPersist jobStatePersist = stateCommonService.
//                saveStatePersist(entityId,stateId,null, JobState.CREATED.toString(), StatusType.JOB.toString(), null);
//
//        stateCommonService.saveLogPersist( buildLogPersist(UUID.randomUUID().toString(),entityId,stateId,
//                JobEvents.CREATE_JOB.toString(), JobState.INIT.toString(), JobState.CREATED.toString(),  StatusType.JOB.toString()));
//
//        List<StatusPersist> statusPersists = new ArrayList<>();
//
//        statusPersists.add(stateCommonService.saveStatePersist(entityId,"stateId2",jobStatePersist.getId(), TaskState.CREATE.toString(), StatusType.TASK.toString(),"asset1"));
//        statusPersists.add(stateCommonService.saveStatePersist(entityId,"stateId3",jobStatePersist.getId(), TaskState.CREATE.toString(), StatusType.TASK.toString(), "asset2"));
//
//        stateCommonService.saveLogPersist( buildLogPersist(UUID.randomUUID().toString(),entityId,"stateId2", TaskEvents.CREATE_TASK.toString(), TaskState.INIT.toString(), TaskState.CREATE.toString(),  StatusType.TASK.toString()));
//        stateCommonService.saveLogPersist( buildLogPersist(UUID.randomUUID().toString(),entityId,"stateId3", TaskEvents.CREATE_TASK.toString(), TaskState.INIT.toString(), TaskState.CREATE.toString(),  StatusType.TASK.toString()));
//        //List<String> taskIds = statusPersists.stream().map(StatusPersist::getId).collect(Collectors.toList());
//        //second step, save job log
        return null;
    }


    public static LogPersist buildLogPersist(String id,String entityId,String stateId, String event, String source, String target,
        String type) {
        return LogPersist.builder().id(id).entityId(entityId).stateId(stateId).type(type).source(source).target(target)
                .event(event).build();

    }



    /*public static List<LogPersist> buildLogPersist(List<String> id,String stateId, String event, String source, String target,
        String type) {
        List<LogPersist> logPersists = new ArrayList<>();
        id.forEach(singleId -> {
            logPersists.add(LogPersist.builder().id(singleId).stateId(stateId).type(type).source(source).target(target)
                .event(event).build());
        });
        return logPersists;
    }*/


    @Override
    public void startJob(String jobId,String stateId) {
        /*long count = stateCommonService.updateStatus(Collections.singletonList(stateId), ImmutableList.of(JobState.CREATED.name(),JobState.STOPPED.name()), JobState.STARTED.name(), null);
        if(count>0){
            LogPersist logPersists = buildLogPersist(UUID.randomUUID().toString(),jobId,stateId, JobEvents.START_JOB.name(), JobState.CREATED.name(), JobState.STARTED.name(),  StatusType.JOB.name());
            stateCommonService.saveLogPersist(logPersists);
        }*/
    }


//    @Override
//    public void stopJob(String jobId,String stateId) {
//        long count = stateService.updateStatus(Collections.singletonList(jobId), ImmutableList.of(JobState.CREATED.name(),JobState.STARTED.name()), JobState.STOPPED.name(), null);
//        if(count>0){
//            List<LogPersist> logPersists = buildLogPersist(UUID.randomUUID().toString()), stateId,JobEvents.STOP_JOB.name(), JobState.STARTED.name(), JobState.STOPPED.name(),  StatusType.JOB.name());
//            stateService.saveLogPersist(logPersists);
//        }
//
//    }


}
