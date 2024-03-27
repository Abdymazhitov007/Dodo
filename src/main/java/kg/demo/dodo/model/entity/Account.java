package kg.demo.dodo.model.entity;

import jakarta.persistence.*;
import kg.demo.dodo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_account")
@Data
@FieldDefaults(level = PRIVATE)
public class Account extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String email;
    Integer tempPassword;
    LocalDateTime dateTimeOfPassword;
    boolean isApproved;


}
