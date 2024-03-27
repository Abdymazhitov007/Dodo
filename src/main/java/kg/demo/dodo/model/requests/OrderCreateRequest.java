package kg.demo.dodo.model.requests;

import kg.demo.dodo.base.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreateRequest {

    List<ProductOrderList> productOrderLists;
    LocalDateTime orderDate;
    PaymentType paymentType;

}
