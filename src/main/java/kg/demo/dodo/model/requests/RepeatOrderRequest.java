package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.demo.dodo.model.entity.enums.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepeatOrderRequest {

    @Schema(description = "Идентификатор заказа", example = "12345")
    Long orderId;

    @Schema(description = "Идентификатор адреса доставки", example = "67890")
    Long addressId;

    @Schema(description = "Дата и время создания заказа", example = "2023-04-15T10:00:00")
    LocalDateTime orderDate;

    @Schema(description = "Способ оплаты заказа", example = "CARD")
    PaymentType paymentType;

}
