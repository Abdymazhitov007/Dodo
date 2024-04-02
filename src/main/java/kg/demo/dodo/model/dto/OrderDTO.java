package kg.demo.dodo.model.dto;

import kg.demo.dodo.base.BaseDTO;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import kg.demo.dodo.model.entity.enums.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDTO extends BaseDTO {

    UserDTO user;
    Double totalPrice;
    Double dodoCoins;
    LocalDateTime orderDate;
    AddressDTO address;
    OrderStatus orderStatus;
    Double discount;
    PaymentType paymentType;

}
