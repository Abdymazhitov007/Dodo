package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.Order;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRep extends BaseRep<Order> {

    List<Order> findByOrderStatus(OrderStatus status);

    Page<Order> findByUserId(Long userId, Pageable pageable);

    Integer countAllByUserId(Long userId);

}
