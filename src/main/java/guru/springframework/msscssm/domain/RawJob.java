package guru.springframework.msscssm.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;


@Data
@Document("ota_job")
@Builder
public class RawJob {

    /**
     * 业务ID对象
     */
    private String jobId;

    private JobState state;

}
