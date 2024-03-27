package kg.demo.dodo.mapper;

import kg.demo.dodo.base.BaseMapper;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
