package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import kg.demo.dodo.base.enums.PaymentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
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
    BigDecimal totalPrice;
    Integer dodoCoins;
    LocalDateTime orderDate;

    BigDecimal discount;

    @Enumerated(EnumType.STRING)
    PaymentType paymentType;





}
