package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    @Schema(description = "Название продукта", example = "Пицца Маргарита")
    String name;

    @Schema(description = "Описание продукта", example = "Классическая пицца с томатным соусом, моцареллой и базиликом")
    String description;

    @Schema(description = "Идентификатор категории продукта", example = "1")
    Long categoryId;

    @Schema(description = "Логотип продукта", format = "binary")
    MultipartFile logo;
}
