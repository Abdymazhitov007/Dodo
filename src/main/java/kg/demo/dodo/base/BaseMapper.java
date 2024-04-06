package kg.demo.dodo.base;




import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.springframework.data.domain.Page;

import java.util.List;

@MappedSuperclass
public interface BaseMapper<ENTITY extends BaseEntity,DTO extends BaseDTO> {
    ENTITY toEntity(DTO dto, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    DTO toDto(ENTITY entity, @Context CycleAvoidingMappingContext context);

    List<ENTITY> toEntities(List<DTO> dtos, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    List<DTO> toDtos(List<ENTITY> entities, @Context CycleAvoidingMappingContext context);
}
