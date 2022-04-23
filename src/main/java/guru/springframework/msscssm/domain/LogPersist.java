package guru.springframework.msscssm.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author youwen.wang
 * @date 2022/4/12 5:25 下午
 */

@Data
@Document("log_persist")
@Builder
public class LogPersist {



    private String id;

    /**
     * event log id
     */
    private String stateId;


    private String  entityId;


    /**
     * log type : job or task
     */

    private String type;

    /**
     * the status before event happen
     */

    private String source;

    /**
     * the status after event happen
     */

    private String target;



    /**
     * event id
     */

    private String event;



}
