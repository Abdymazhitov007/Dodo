package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.UserMapper;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.entity.User;
import kg.demo.dodo.repository.UserRep;
import kg.demo.dodo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRep, UserDTO, UserMapper> implements UserService {

    public UserServiceImpl(UserRep rep, UserMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public UserDTO getByAccountId(Long accountId) {
        return mapper.toDto(rep.findByAccount_Id(accountId), context);
    }
}
