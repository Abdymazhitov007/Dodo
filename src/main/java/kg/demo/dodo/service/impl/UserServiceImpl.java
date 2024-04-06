package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.UserMapper;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.entity.User;
import kg.demo.dodo.model.requests.SetRoleRequest;
import kg.demo.dodo.model.requests.UserUpdateRequest;
import kg.demo.dodo.model.response.UserInfoResponse;
import kg.demo.dodo.model.response.UserListResponse;
import kg.demo.dodo.repository.UserRep;
import kg.demo.dodo.service.*;
import kg.demo.dodo.util.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRep, UserDTO, UserMapper> implements UserService {



    public UserServiceImpl(UserRep rep, UserMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public List<UserListResponse> getUserList() {
        return rep.findUserList();
    }

    @Override
    public UserListResponse setRole(String token, SetRoleRequest request) {
        return null;
    }


    @Override
    public UserDTO getByAccountId(Long accountId) {
        return mapper.toDto(rep.findByAccount_Id(accountId), context);
    }



}
