package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.Order;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRep extends BaseRep<Order> {

    List<Order> findByOrderStatus(OrderStatus status);

    List<Order> findByUserId(Long userId);

}
