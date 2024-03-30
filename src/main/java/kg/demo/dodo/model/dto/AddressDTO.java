package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddressDTO extends BaseDTO {

    String city;
    String street;
    String num;
    String comment;
    UserDTO user;

}
