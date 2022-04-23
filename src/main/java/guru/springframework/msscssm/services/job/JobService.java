package guru.springframework.msscssm.services.job;

public interface JobService {

    String createJob(String entityId, String stateId);

    void startJob(String jobId, String stateId);

    // void stopJob(String jobId,String stateId);

}
