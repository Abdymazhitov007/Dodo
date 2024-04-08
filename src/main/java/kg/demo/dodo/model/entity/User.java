package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_user")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String phone;
    String name;
    Double dodoCoins;

    @OneToOne
    Account account;
}
