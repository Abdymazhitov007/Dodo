package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {

    @Schema(description = "Электронная почта", example = "example@example.com")
    String email;

    @Schema(description = "Имя пользователя", example = "John")
    String name;

    @Schema(description = "Номер телефона", example = "+1234567890")
    String phone;
}
