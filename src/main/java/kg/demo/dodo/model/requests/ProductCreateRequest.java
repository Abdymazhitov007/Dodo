package kg.demo.dodo.model.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    String name;
    String description;
    Long categoryId;
    Long sizeId;
    BigDecimal price;
    MultipartFile logo;

}
