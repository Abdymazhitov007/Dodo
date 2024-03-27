package kg.demo.dodo.base;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kg.demo.dodo.base.enums.Status;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseDTO {
    protected Long id;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
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
