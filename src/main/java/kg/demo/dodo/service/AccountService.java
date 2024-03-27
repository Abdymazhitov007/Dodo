package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.AccountDTO;

public interface AccountService extends BaseService<AccountDTO> {

    AccountDTO findByEmail(String email);

}
