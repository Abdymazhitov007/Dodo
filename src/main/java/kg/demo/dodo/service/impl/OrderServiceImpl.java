package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.OrderMapper;
import kg.demo.dodo.model.dto.OrderDTO;
import kg.demo.dodo.model.entity.Order;
import kg.demo.dodo.repository.OrderRep;
import kg.demo.dodo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRep, OrderDTO, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRep rep, OrderMapper mapper) {
        super(rep, mapper);
    }
}
