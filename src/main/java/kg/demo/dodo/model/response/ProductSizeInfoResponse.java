package kg.demo.dodo.model.response;

import kg.demo.dodo.model.dto.SizeDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeInfoResponse {

    String size;
    Double price;

}
