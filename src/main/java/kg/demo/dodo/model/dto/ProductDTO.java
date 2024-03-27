package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO {

    String name;
    String logo;
    String description;

    CategoryDTO category;

}
