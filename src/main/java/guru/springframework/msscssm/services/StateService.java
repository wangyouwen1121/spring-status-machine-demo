package guru.springframework.msscssm.services;

import guru.springframework.msscssm.domain.LogPersist;
import guru.springframework.msscssm.domain.StatusPersist;

import java.util.Collection;
import java.util.List;

/**
 * @author youwen.wang
 * @date 2022/4/13 8:24 上午
 */

public interface StateService {

    StatusPersist saveStatePersist(String entityId,String stateId,String parentId, String state, String type, String assetId);

    LogPersist saveLogPersist(LogPersist logPersists);

    List<LogPersist> getLog(String id);

    StatusPersist getStatePersist(String id);

    long updateStatus(List<String> id, List<String> currentStatus, String status, String desc);




}
