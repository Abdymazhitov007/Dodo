package kg.demo.dodo.model.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeCreateRequest {

    Double price;
    Long sizeId;
    Long productId;

}