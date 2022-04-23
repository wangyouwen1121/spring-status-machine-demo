package guru.springframework.msscssm.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import guru.springframework.msscssm.domain.StatusPersist;

/**
 * @author youwen.wang
 * @date 2022/4/12 11:04 下午
 */

@Component
public class StatusTable {

    @Autowired
    private MongoTemplate mongoTemplate;

//    public Collection<StatusPersist> insertAll(Collection<StatusPersist> statusPersist) {
//        return mongoTemplate.insertAll(statusPersist);
//    }


    public StatusPersist insert(StatusPersist statusPersist) {
        return mongoTemplate.insert(statusPersist);
    }


    public StatusPersist findById(String id) {
        Criteria criteria = createIdCriteria(id);
        return mongoTemplate.findOne(Query.query(criteria), StatusPersist.class);
    }

    private Criteria createIdCriteria(String id) {
        return Criteria.where("id").is(id);
    }

    public long batchUpdateStatusAndDesc(List<String> ids, List<String> currentStatus, String status, String desc) {
        Criteria criteria = Criteria.where("_id").in(ids).and("state").in(currentStatus);
        Update update = new Update().set("state", status).set("desc", desc);
        return mongoTemplate.updateMulti(Query.query(criteria), update, StatusPersist.class).getModifiedCount();
    }

    public void update(StatusPersist statusPersist){
        Criteria criteria = Criteria.where("_id").is(statusPersist.getId());
        Update update = new Update().set("state", statusPersist.getState());
        mongoTemplate.updateFirst(Query.query(criteria),update,StatusPersist.class);
    }

}
