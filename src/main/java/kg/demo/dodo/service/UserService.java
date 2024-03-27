package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.UserDTO;

public interface UserService extends BaseService<UserDTO> {

    UserDTO getByAccountId(Long accountId);

}
