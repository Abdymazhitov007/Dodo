package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.model.dto.SizeDTO;
import kg.demo.dodo.model.entity.Size;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface SizeMapper extends BaseMapper<Size, SizeDTO> {
}
