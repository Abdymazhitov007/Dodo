package kg.demo.dodo.model.requests;

import kg.demo.dodo.model.entity.enums.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepeatOrderRequest {

    Long orderId;
    Long addressId;
    LocalDateTime orderDate;
    PaymentType paymentType;

}
