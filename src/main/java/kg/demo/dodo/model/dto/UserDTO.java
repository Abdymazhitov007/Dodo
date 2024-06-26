package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {

    String phone;
    String name;
    Double dodoCoins;
    AccountDTO account;

    public String toString() {
        return "UserDTO("+ super.toString() + ", phone=" + this.getPhone() + ", name=" + this.getName() + ", dodoCoins=" + this.getDodoCoins() + ", account=" + this.getAccount() + ")";
    }
}
