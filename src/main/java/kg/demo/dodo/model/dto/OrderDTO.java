package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import kg.demo.dodo.base.enums.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends BaseDTO {

    UserDTO user;
    BigDecimal totalPrice;
    Integer dodoCoins;
    LocalDateTime orderDate;
    BigDecimal discount;
    PaymentType paymentType;

}
