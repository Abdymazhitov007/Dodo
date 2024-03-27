package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.model.dto.AddressDTO;
import kg.demo.dodo.model.entity.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {
}
