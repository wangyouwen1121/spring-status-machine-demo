package guru.springframework.msscssm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.LogPersist;

/**
 * @author youwen.wang
 * @date 2022/4/12 11:07 下午
 */

@Component
public class LogTable {

    @Autowired
    private MongoTemplate mongoTemplate;

//    public Collection<LogPersist> insertAll(Collection<LogPersist> logPersist) {
//        return mongoTemplate.insertAll(logPersist);
//    }


    public LogPersist insert(LogPersist logPersist) {
        return mongoTemplate.insert(logPersist);
    }

    public List<LogPersist> findById(String id) {
        Criteria criteria = createIdCriteria(id);
        return mongoTemplate.find(Query.query(criteria), LogPersist.class);

    }

    private Criteria createIdCriteria(String id) {
        return Criteria.where("id").is(id);
    }
}
