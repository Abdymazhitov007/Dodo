package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrderList {
    @Schema(description = "Идентификатор продукта", example = "1")
    Long productSizeId;

    @Schema(description = "Количество продуктов", example = "2")
    Integer quantity;

    @Schema(description = "Цена за единицу продукта", example = "10.99")
    Double price;

}
