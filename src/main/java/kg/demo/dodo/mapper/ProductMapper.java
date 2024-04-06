package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.base.CycleAvoidingMappingContext;
import kg.demo.dodo.model.dto.ProductDTO;
import kg.demo.dodo.model.entity.Product;
import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDTO> {
}
