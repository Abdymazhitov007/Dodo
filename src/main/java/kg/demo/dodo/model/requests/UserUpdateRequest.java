package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.demo.dodo.model.dto.AccountDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {


    @Schema(description = "Идентификатор пользователя", example = "12345")
    Long id;

    @Schema(description = "Номер телефона пользователя", example = "+1234567890")
    String phone;

    @Schema(description = "Имя пользователя", example = "John")
    String name;

    @Schema(description = "Количество Dodo монет пользователя", example = "100.0")
    Double dodoCoins;

}
