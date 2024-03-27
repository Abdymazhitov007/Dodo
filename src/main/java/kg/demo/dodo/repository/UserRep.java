package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends BaseRep<User> {

    User findByAccount_Id(Long accountId);

}
