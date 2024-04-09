package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeCreateRequest {

    @Schema(description = "Цена продукта", example = "10.99")
    Double price;

    @Schema(description = "Идентификатор размера", example = "1")
    Long sizeId;

    @Schema(description = "Идентификатор продукта", example = "12345")
    Long productId;

}
