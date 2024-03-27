package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_order_product")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
    Product product;

    @ManyToOne
    Order order;

    BigDecimal price;

}
