package kg.demo.dodo.base;


import jakarta.persistence.*;
import kg.demo.dodo.model.entity.enums.Status;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@MappedSuperclass
public abstract class BaseEntity {

    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @PrePersist
    protected void onCreate(){
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        status = Status.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedDate = LocalDateTime.now();
    }

}
