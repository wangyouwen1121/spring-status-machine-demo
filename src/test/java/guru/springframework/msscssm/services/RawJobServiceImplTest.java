package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.RawJob;
import guru.springframework.msscssm.domain.JobEvents;
import guru.springframework.msscssm.domain.JobState;
import guru.springframework.msscssm.repository.JobTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RawJobServiceImplTest {

    @Autowired
    JobService jobService;

    @Autowired
    JobTable jobTable;


    RawJob rawJob;

    @BeforeEach
    void setUp() {
        rawJob = RawJob.builder().state(JobState.STOPPED).build();
    }


    @Test
    void startJob() {
        RawJob savedRawJob = jobService.createJob(rawJob);
        System.out.println("Should be NEW");
        System.out.println(savedRawJob.getState());
        StateMachine<JobState, JobEvents> sm = jobService.startJob(savedRawJob.getJobId());
        RawJob startJob = jobTable.findById(savedRawJob.getJobId());
        System.out.println("should be start");
        System.out.println(sm.getState().getId());
        System.out.println(startJob);
    }


    @Test
    void testStopJob() {
        RawJob savedRawJob = jobService.createJob(rawJob);
        StateMachine<JobState, JobEvents> preAuthSM = jobService.startJob(savedRawJob.getJobId());
        if (preAuthSM.getState().getId() == JobState.STARTED) {
            System.out.println("status is started");
            StateMachine<JobState, JobEvents> authSM = jobService.stopJob(savedRawJob.getJobId());
            System.out.println("Result of status: " + authSM.getState().getId());
        } else {
            System.out.println("failed to stop job");
        }
    }
}