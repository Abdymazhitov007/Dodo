package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.OrderMapper;
import kg.demo.dodo.model.dto.OrderDTO;
import kg.demo.dodo.model.entity.Order;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import kg.demo.dodo.repository.OrderRep;
import kg.demo.dodo.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service

public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDTO, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRep rep, OrderMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public List<OrderDTO> getByOrderStatus(OrderStatus status) {
        return mapper.toDtos(rep.findByOrderStatus(status), context);
    }

    @Override
    public List<OrderDTO> getByUserId(Long userId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "orderDate"));
        return mapper.toDtos(rep.findByUserId(userId,  pageable).getContent(), context);
    }

    @Override
    public Integer getNumOfOrderByUserId(Long userId) {
        return rep.countAllByUserId(userId);
    }


    @Override
    public void checkOrders() {
        List<OrderDTO> orderDTO = getByOrderStatus(OrderStatus.NEW);
        orderDTO.forEach(x -> {
            System.out.println("Order(id = " + x.getId() + ", orderDate = " + x.getOrderDate() + ", orderStatus = " + x.getOrderStatus());
            if (LocalDateTime.now().until(x.getOrderDate(), ChronoUnit.MINUTES) <= 30) {
                x.setOrderStatus(OrderStatus.PREPARING);
                update(x);
                System.out.println("Changed: Order(id = " + x.getId() + ", orderDate = " + x.getOrderDate() + ", orderStatus = " + x.getOrderStatus());
            }
        });
    }


}
