package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {

    String phone;
    String name;
    Integer dodoCoins;
    AccountDTO account;

}
