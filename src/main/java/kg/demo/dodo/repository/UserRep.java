package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.User;
import kg.demo.dodo.model.response.UserListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRep extends BaseRep<User> {

    User findByAccount_Id(Long accountId);

    @Query(value = "select u.id, u.name, u.phone, a.email from tb_user u join tb_account a on u.account_id = a.id where u.status = 'ACTIVE'", nativeQuery = true)
    List<UserListResponse> findUserList();
}
