package guru.springframework.msscssm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author youwen.wang
 * @date 2022/4/12 5:03 下午
 */
@Data
@Document("status_persist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusPersist {

    /**
     * state machine id
     */
    @Id
    public String id;

    /**
     * if task , id= taskId. if job, id=jobId
     */
    public String entityId;

    private String state;

    /**
     * job/task
     */

    private String type;

    /**
     * if job, parentId is null, if task , parentId is its jobId
     */

    private String parentId;

    /**
     * task的assetIds
     */
    public String assetId;

    public String desc;

}
