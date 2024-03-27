package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.AccountMapper;
import kg.demo.dodo.model.dto.AccountDTO;
import kg.demo.dodo.model.entity.Account;
import kg.demo.dodo.repository.AccountRep;
import kg.demo.dodo.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRep, AccountDTO, AccountMapper> implements AccountService {

    public AccountServiceImpl(AccountRep rep, AccountMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public AccountDTO findByEmail(String email) {
        return mapper.toDto(rep.findByEmail(email), context);
    }
}
