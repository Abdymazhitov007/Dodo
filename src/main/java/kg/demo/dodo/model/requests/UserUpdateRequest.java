package kg.demo.dodo.model.requests;

import kg.demo.dodo.model.dto.AccountDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    Long id;
    String phone;
    String name;
    Double dodoCoins;

}
