package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.model.dto.ProductSizeDTO;
import kg.demo.dodo.model.entity.ProductSize;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface ProductSizeMapper extends BaseMapper<ProductSize, ProductSizeDTO> {
}
