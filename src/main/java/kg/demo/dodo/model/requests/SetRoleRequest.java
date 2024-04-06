package kg.demo.dodo.model.requests;

import kg.demo.dodo.model.entity.enums.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetRoleRequest {

    Long userId;
    Role role;

}
