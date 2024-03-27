package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.OrderProductMapper;
import kg.demo.dodo.model.dto.OrderProductDTO;
import kg.demo.dodo.model.entity.OrderProduct;
import kg.demo.dodo.repository.OrderProductRep;
import kg.demo.dodo.service.OrderProductService;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, OrderProductRep, OrderProductDTO, OrderProductMapper> implements OrderProductService {
    public OrderProductServiceImpl(OrderProductRep rep, OrderProductMapper mapper) {
        super(rep, mapper);
    }
}
