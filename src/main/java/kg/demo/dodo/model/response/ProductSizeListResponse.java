package kg.demo.dodo.model.response;

import kg.demo.dodo.model.dto.CategoryDTO;

import java.math.BigDecimal;

public interface ProductSizeListResponse {

    Long getId();
    String getName();
    String getLogo();
    String getDescription();
    String getCategory();
    String getSize();
    BigDecimal getPrice();
}
