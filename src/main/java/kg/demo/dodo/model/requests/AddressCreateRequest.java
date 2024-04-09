package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.demo.dodo.model.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreateRequest {

    @Schema(description = "Название города", example = "Москва")
    String city;

    @Schema(description = "Название улицы", example = "Ленина")
    String street;

    @Schema(description = "Номер дома", example = "123")
    String num;

    @Schema(description = "Комментарий", example = "Ближайшая остановка: улица Ленина, дом 123")
    String comment;

}
