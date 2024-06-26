package kg.demo.dodo.model.dto;


import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderProductDTO extends BaseDTO {

    ProductSizeDTO productSize;
    OrderDTO order;

    Integer quantity;
    Double price;

}
