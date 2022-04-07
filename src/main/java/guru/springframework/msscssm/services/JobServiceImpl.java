package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.RawJob;
import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.repository.JobTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by jt on 2019-08-10.
 */

@Service
public class JobServiceImpl implements JobService {
    public static final String JOB_ID_HEADER = "job_id";

    @Autowired
    private JobTable jobTable;

    private final StateMachineFactory<JobState, JobEvents> stateMachineFactory;
    private final JobStateChangeInterceptor jobStateChangeInterceptor;

    public JobServiceImpl(JobTable jobTable, StateMachineFactory<JobState, JobEvents> stateMachineFactory, JobStateChangeInterceptor jobStateChangeInterceptor) {
        this.jobTable = jobTable;
        this.stateMachineFactory = stateMachineFactory;
        this.jobStateChangeInterceptor = jobStateChangeInterceptor;
    }

    @Override
    public RawJob createJob(RawJob rawJob) {
        rawJob.setState(JobState.STOPPED);
        rawJob.setJobId(UUID.randomUUID().toString());
        return jobTable.insert(rawJob);
    }


    @Override
    public StateMachine<JobState, JobEvents> startJob(String jobId) {
        StateMachine<JobState, JobEvents> sm = build(jobId);
        sendEvent(jobId, sm, JobEvents.START_JOB);
        return sm;
    }


    @Override
    public StateMachine<JobState, JobEvents> stopJob(String jobId) {
        StateMachine<JobState, JobEvents> sm = build(jobId);
        sendEvent(jobId, sm, JobEvents.STOP_JOB);
        return sm;
    }


    private void sendEvent(String jobId, StateMachine<JobState, JobEvents> sm, JobEvents event){
        Message msg = MessageBuilder.withPayload(event)
                .setHeader(JOB_ID_HEADER, jobId)
                .build();
        sm.sendEvent(msg);
    }

    private StateMachine<JobState, JobEvents> build(String jobId){
        RawJob rawJob = jobTable.findById(jobId);
        StateMachine<JobState, JobEvents> sm = stateMachineFactory.getStateMachine(rawJob.getJobId());
        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(jobStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(rawJob.getState(), null, null, null));
                });

        sm.start();
        return sm;
    }
}
