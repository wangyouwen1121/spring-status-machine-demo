package guru.springframework.msscssm.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.msscssm.domain.LogPersist;
import guru.springframework.msscssm.domain.StatusPersist;
import guru.springframework.msscssm.repository.LogTable;
import guru.springframework.msscssm.repository.StatusTable;
import org.springframework.util.StringUtils;

/**
 * @author youwen.wang
 * @date 2022/4/13 8:25 上午
 */
@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StatusTable statusTable;

    @Autowired
    private LogTable logTable;


    @Override
    public StatusPersist saveStatePersist(String entityId,String stateId,String parentId, String state, String type, String assetId) {
        StatusPersist statusPersist;
        if (StringUtils.isEmpty(assetId)) {
            statusPersist= saveSingleStatus(entityId, stateId, parentId, state, type).build();
        } else {
            statusPersist= saveSingleStatus(null, stateId, parentId, state, type).assetId(assetId).build();
        }
       return statusTable.insert(statusPersist);
    }
    


    @Override
    public LogPersist saveLogPersist(LogPersist logPersists) {


        LogPersist logPersist = LogPersist.builder()
                .id(logPersists.getId())
                .entityId(logPersists.getEntityId())
                .stateId(logPersists.getStateId())
                .event(logPersists.getEvent())
                .source(logPersists.getSource())
                .target(logPersists.getTarget())
                .type(logPersists.getType())
                .build();

        return logTable.insert(logPersist);

    }

    @Override
    public List<LogPersist> getLog(String id) {
       return logTable.findById(id);
    }

    @Override
    public StatusPersist getStatePersist(String id) {
      return statusTable.findById(id);
    }

    @Override
    public long updateStatus(List<String> ids, List<String> currentStatus, String status, String desc) {
       return  statusTable.batchUpdateStatusAndDesc(ids,currentStatus,status,desc);
    }

    /**
     *
     * @param
     * @param parentId
     * @param state
     * @param type
     * @return
     */

    public  StatusPersist.StatusPersistBuilder saveSingleStatus(String entityId,String stateId,String parentId,String state,String type){

        return  StatusPersist.builder()
                .entityId(StringUtils.isEmpty(entityId)?UUID.randomUUID().toString():entityId)
                .id(stateId)
                .state(state)
                .type(type)
                .parentId(parentId);
    }

}
