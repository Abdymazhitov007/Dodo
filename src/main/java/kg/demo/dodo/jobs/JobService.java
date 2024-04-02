package kg.demo.dodo.jobs;

import kg.demo.dodo.model.dto.OrderDTO;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import kg.demo.dodo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobService {

    private final OrderService orderService;

    @Scheduled(fixedRate = 60000)
    public void changeOrderStatus(){

        List<OrderDTO> orderDTO = orderService.getByOrderStatus(OrderStatus.NEW);
        orderDTO.forEach(x -> {
            System.out.println("Order(id = " + x.getId() + ", orderDate = " + x.getOrderDate() + ", orderStatus = " + x.getOrderStatus());
            if (LocalDateTime.now().until(x.getOrderDate(), ChronoUnit.MINUTES) <= 30) {
                x.setOrderStatus(OrderStatus.PREPARING);
                orderService.update(x);
                System.out.println("Changed: Order(id = " + x.getId() + ", orderDate = " + x.getOrderDate() + ", orderStatus = " + x.getOrderStatus());
            }
        });

    }

}
