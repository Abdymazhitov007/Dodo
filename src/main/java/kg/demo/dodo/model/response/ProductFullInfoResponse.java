package kg.demo.dodo.model.response;

import kg.demo.dodo.model.dto.CategoryDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFullInfoResponse {

    String name;
    String logo;
    String description;
    String category;

    List<ProductSizeInfoResponse> productSizeInfo;

}
