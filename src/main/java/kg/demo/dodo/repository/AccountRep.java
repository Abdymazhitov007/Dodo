package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRep extends BaseRep<Account> {
    Account findByEmail(String email);

}
