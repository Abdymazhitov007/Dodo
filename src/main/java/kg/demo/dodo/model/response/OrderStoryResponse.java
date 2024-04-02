package kg.demo.dodo.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStoryResponse {

    Long id;
    LocalDateTime orderDate;
    AddressResponse address;
    List<ProductResponse> products;
    Double totalPrice;

}
