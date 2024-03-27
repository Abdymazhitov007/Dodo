package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_size")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    String name;



}
