package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class AccountDTO extends BaseDTO {

    String email;
    Integer tempPassword;
    LocalDateTime dateTimeOfPassword;
    boolean isApproved;

}
