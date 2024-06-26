package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.response.UserListResponse;

import java.util.List;

public interface UserService extends BaseService<UserDTO> {

    UserDTO getByAccountId(Long accountId);

    Integer getNumOfAddressByUserId(Long userId);


}
