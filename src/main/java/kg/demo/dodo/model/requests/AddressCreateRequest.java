package kg.demo.dodo.model.requests;

import kg.demo.dodo.model.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreateRequest {

    String city;
    String street;
    String num;
    String comment;
    Long userId;

}
