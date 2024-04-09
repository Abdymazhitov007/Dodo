package kg.demo.dodo.model.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.demo.dodo.model.entity.enums.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateRequest {

    @Schema(description = "Список продуктов в заказе")
    List<ProductOrderList> productOrderLists;

    @Schema(description = "Идентификатор адреса доставки", example = "12345")
    Long addressId;

    @Schema(description = "Дата и время создания заказа")
    LocalDateTime orderDate;

    @Schema(description = "Способ оплаты заказа", example = "CARD")
    PaymentType paymentType;

}
