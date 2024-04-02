package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.OrderDTO;
import kg.demo.dodo.model.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService  extends BaseService<OrderDTO> {

    List<OrderDTO> getByOrderStatus(OrderStatus status);

    List<OrderDTO> getByUserId(Long userId);

}
