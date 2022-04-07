package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.RawJob;
import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import org.springframework.statemachine.StateMachine;


public interface JobService {

    RawJob createJob(RawJob rawJob);

    StateMachine<JobState, JobEvents> startJob(String jobId);

    StateMachine<JobState, JobEvents> stopJob(String jobId);
}
