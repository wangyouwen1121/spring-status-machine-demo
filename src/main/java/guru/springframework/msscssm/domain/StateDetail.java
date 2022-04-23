package guru.springframework.msscssm.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youwen.wang
 * @date 2022/4/23 8:22 下午
 */
@Data
public class StateDetail extends StatusPersist implements Serializable {

    private String eventName;

}
