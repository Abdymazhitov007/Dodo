package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.model.dto.OrderProductDTO;
import kg.demo.dodo.model.entity.OrderProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface OrderProductMapper extends BaseMapper<OrderProduct, OrderProductDTO> {
}
