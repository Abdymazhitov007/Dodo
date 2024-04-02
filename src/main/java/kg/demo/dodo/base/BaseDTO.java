package kg.demo.dodo.base;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kg.demo.dodo.model.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@ToString
@Setter
@Getter
public abstract class BaseDTO {
    protected Long id;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected Status status;


}
