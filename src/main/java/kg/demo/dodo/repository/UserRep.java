package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.OrderProduct;
import kg.demo.dodo.model.entity.User;
import kg.demo.dodo.model.response.AddressListResponse;
import kg.demo.dodo.model.response.UserListResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRep extends BaseRep<User> {

    User findByAccount_Id(Long accountId);


    @Query(value = "select count(*) from tb_address where user_id = :userId and status = 'ACTIVE'", nativeQuery = true)
    Integer countAddressByUserId(Long userId);

}
