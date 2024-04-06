package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.OrderProductDTO;
import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.model.requests.RepeatOrderRequest;
import kg.demo.dodo.model.response.OrderStoryResponse;
import kg.demo.dodo.model.response.Response;

import java.util.List;

public interface OrderProductService  extends BaseService<OrderProductDTO> {
    Response<OrderStoryResponse> create(OrderCreateRequest request, String token, int lang);

    List<OrderStoryResponse> getOrderStory(String token, int pageNum, int pageSize, int lang);

    List<OrderProductDTO> getByOrderId(Long orderId);

    OrderCreateRequest repeatOrder(String token, Long orderId, int lang);

    OrderStoryResponse toOrderStoryResponse(Long orderId);

    List<OrderProductDTO> getAllByOrderId(Long orderId);
}
