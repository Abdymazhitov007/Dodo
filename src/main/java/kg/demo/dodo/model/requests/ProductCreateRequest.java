package kg.demo.dodo.model.requests;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class ProductCreateRequest {

    String name;
    String description;
    Long categoryId;
    Long sizeId;
    BigDecimal price;
    MultipartFile logo;

}
