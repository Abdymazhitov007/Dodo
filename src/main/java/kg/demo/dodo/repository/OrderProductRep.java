package kg.demo.dodo.repository;

import kg.demo.dodo.base.BaseRep;
import kg.demo.dodo.model.entity.OrderProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRep extends BaseRep<OrderProduct> {

//    @Query(value = "select * from tb_order_product where status = 'ACTIVE' and id = :orderId", nativeQuery = true)
    List<OrderProduct> findByOrderId(Long orderId);

}
