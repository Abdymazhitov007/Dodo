package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import kg.demo.dodo.model.entity.enums.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_order")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
    User user;
    Double totalPrice;
    Double dodoCoins;
    LocalDateTime orderDate;

    @ManyToOne
    Address address;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    Double discount;

    @Enumerated(EnumType.STRING)
    PaymentType paymentType;





}
