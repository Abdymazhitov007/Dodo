package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidateEmailReq {

    @Schema(description = "Электронная почта", example = "example@example.com")
    String email;

    @Schema(description = "Временный пароль", example = "123456")
    Integer tempPassword;

}
