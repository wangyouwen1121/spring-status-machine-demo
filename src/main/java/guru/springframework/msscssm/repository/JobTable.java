package guru.springframework.msscssm.repository;

import guru.springframework.msscssm.domain.JobState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.RawJob;


@Component
public class JobTable {

    @Autowired
    private MongoTemplate mongoTemplate;

    public RawJob insert(RawJob rawJob) {
        return mongoTemplate.insert(rawJob);
    }

    public RawJob findById(String jobId) {
        Criteria criteria = createIdCriteria(jobId);
        return mongoTemplate.findOne(Query.query(criteria), RawJob.class);
    }

    public long updateStatus(String jobId, JobState status) {
        Query query = Query.query(createIdCriteria(jobId));
        Update update = new Update().set("status", status);
        return mongoTemplate.updateFirst(query, update, RawJob.class).getModifiedCount();

    }

    private Criteria createIdCriteria(String jobId) {
        return Criteria.where("jobId").is(jobId);
    }


}
