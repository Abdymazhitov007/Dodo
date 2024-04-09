package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreateRequest {


    @Schema(description = "Название категории продуктов", example = "Пицца")
    String name;

    @Schema(description = "Описание категории продуктов", example = "Широкий ассортимент пицц: от классических до авторских")
    String definition;


}
