package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.OrderProductDTO;
import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.model.requests.RepeatOrderRequest;
import kg.demo.dodo.model.response.OrderStoryResponse;

import java.util.List;

public interface OrderProductService  extends BaseService<OrderProductDTO> {
    String create(OrderCreateRequest request, String token, int lang);

    List<OrderStoryResponse> getOrderStory(String token, int lang);

    List<OrderProductDTO> getByOrderId(Long orderId);

    String repeatOrder(String token, RepeatOrderRequest request, int lang);

    List<OrderProductDTO> getAllByOrderId(Long orderId);
}
