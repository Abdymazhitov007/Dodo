package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.model.entity.enums.OrderStatus;
import kg.demo.dodo.mapper.OrderProductMapper;
import kg.demo.dodo.model.dto.*;
import kg.demo.dodo.model.entity.OrderProduct;
import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.model.requests.ProductOrderList;
import kg.demo.dodo.model.requests.RepeatOrderRequest;
import kg.demo.dodo.model.response.AddressResponse;
import kg.demo.dodo.model.response.OrderStoryResponse;
import kg.demo.dodo.model.response.ProductResponse;
import kg.demo.dodo.model.response.Response;
import kg.demo.dodo.repository.OrderProductRep;
import kg.demo.dodo.service.*;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service

public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, OrderProductRep, OrderProductDTO, OrderProductMapper> implements OrderProductService {

    private final AuthService authService;
    private final OrderService orderService;
    private final UserService userService;
    private final ProductSizeService productSizeService;
    private final AddressService addressService;



    public OrderProductServiceImpl(OrderProductRep rep, OrderProductMapper mapper, AuthService authService, OrderService orderService, UserService userService, ProductSizeService productSizeService, AddressService addressService, ProductService productService) {
        super(rep, mapper);
        this.authService = authService;
        this.orderService = orderService;
        this.userService = userService;
        this.productSizeService = productSizeService;
        this.addressService = addressService;
    }

    @Override
    public Response<OrderStoryResponse> create(OrderCreateRequest request, String token, int lang) {

        UserDTO user = userService.findById(authService.getUserIdByToken(token, lang));
        Double totalPrice = 0.0;
        Double discount = 0.0;

        OrderDTO order = new OrderDTO();
        order.setDiscount(0.0);
        order.setUser(user);
        order.setOrderDate(request.getOrderDate());
        order.setPaymentType(request.getPaymentType());
        order.setAddress(addressService.findById(request.getAddressId()));

        if (LocalDateTime.now().isBefore(request.getOrderDate())) {
            if (LocalDateTime.now().until(request.getOrderDate(), ChronoUnit.MINUTES) <= 30) {
                order.setOrderStatus(OrderStatus.PREPARING);
            } else {
                order.setOrderStatus(OrderStatus.NEW);
            }
        }else {
            throw new IllegalArgumentException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "orderTimePast"));
        }

        for (ProductOrderList item : request.getProductOrderLists()) {
            ProductSizeDTO product = productSizeService.findById(item.getProductSizeId());
            if (item.getPrice() == 0) {
                discount += product.getPrice() / 10 + product.getPrice();
                order.setDiscount(order.getDiscount() + product.getPrice());
            }


            if (item.getQuantity() < 1) {
                throw new RuntimeException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "quantityLessOne"));
            }
            totalPrice += product.getPrice() * item.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        order.setDodoCoins((totalPrice - order.getDiscount()) / 10);

        user.setDodoCoins(user.getDodoCoins() - discount + order.getDodoCoins());


        userService.save(user);

        OrderDTO orderFromDB = orderService.save(order);

        for (ProductOrderList item : request.getProductOrderLists()) {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setOrder(orderFromDB);
            orderProductDTO.setQuantity(item.getQuantity());

            ProductSizeDTO product = productSizeService.findById(item.getProductSizeId());
            orderProductDTO.setProductSize(product);
            orderProductDTO.setPrice(item.getPrice());

            save(orderProductDTO);
        }

        Response<OrderStoryResponse> response = new Response<>();
        response.setData(toOrderStoryResponse(orderFromDB.getId()));
        response.setMessage(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "orderPreparing"));

        return response;
    }

    @Override
    public List<OrderStoryResponse> getOrderStory(String token, int pageNum, int pageSize, int lang) {

        UserDTO userDTO = userService.findById(authService.getUserIdByToken(token, lang));
        List<OrderStoryResponse> result = new ArrayList<>();

        for (OrderDTO item: orderService.getByUserId(userDTO.getId(), pageNum, pageSize)) {

            OrderStoryResponse response = new OrderStoryResponse();

            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setId(item.getAddress().getId());
            addressResponse.setCity(item.getAddress().getCity());
            addressResponse.setNum(item.getAddress().getNum());
            addressResponse.setStreet(item.getAddress().getStreet());

            response.setAddress(addressResponse);
            response.setId(item.getId());
            response.setOrderDate(item.getOrderDate());
            response.setTotalPrice(item.getTotalPrice());

            List<ProductResponse> productResponses = new ArrayList<>();
            for (OrderProductDTO x: getByOrderId(item.getId())) {
                ProductResponse productResponse = new ProductResponse();
                productResponse.setId(x.getProductSize().getId());
                productResponse.setName(x.getProductSize().getProduct().getName());
                productResponse.setQuantity(x.getQuantity());
                productResponse.setPrice(x.getProductSize().getPrice());
                productResponse.setSize(x.getProductSize().getSize().getName());
                productResponse.setCategory(x.getProductSize().getProduct().getCategory().getName());

                productResponses.add(productResponse);
            }
            response.setProducts(productResponses);

            result.add(response);
        }

        return result;
    }



    @Override
    public OrderCreateRequest repeatOrder(String token, Long orderId, int lang) {
        UserDTO userDTO = userService.findById(authService.getUserIdByToken(token, lang));
        OrderDTO orderDTO = orderService.findById(orderId);

        if (!orderDTO.getUser().getId().equals(userDTO.getId())) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "wrongOrderId"));
        }

        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setAddressId(orderDTO.getAddress().getId());
        orderCreateRequest.setPaymentType(orderDTO.getPaymentType());
        orderCreateRequest.setOrderDate(LocalDateTime.now());

        List<ProductOrderList> productOrderLists = new ArrayList<>();
        for (OrderProductDTO item : getAllByOrderId(orderId)) {
            ProductOrderList productOrderList = new ProductOrderList();
            productOrderList.setPrice(item.getProductSize().getPrice());
            productOrderList.setProductSizeId(item.getProductSize().getId());
            productOrderList.setQuantity(item.getQuantity());
            productOrderLists.add(productOrderList);
        }
        orderCreateRequest.setProductOrderLists(productOrderLists);

        return orderCreateRequest;
    }

    @Override
    public OrderStoryResponse toOrderStoryResponse(Long orderId) {

        OrderDTO orderDTO = orderService.findById(orderId);

        OrderStoryResponse response = new OrderStoryResponse();

        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(orderDTO.getAddress().getId());
        addressResponse.setCity(orderDTO.getAddress().getCity());
        addressResponse.setNum(orderDTO.getAddress().getNum());
        addressResponse.setStreet(orderDTO.getAddress().getStreet());

        response.setAddress(addressResponse);
        response.setId(orderDTO.getId());
        response.setOrderDate(orderDTO.getOrderDate());
        response.setTotalPrice(orderDTO.getTotalPrice());

        List<ProductResponse> productResponses = new ArrayList<>();
        for (OrderProductDTO x: getByOrderId(orderDTO.getId())) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(x.getProductSize().getId());
            productResponse.setName(x.getProductSize().getProduct().getName());
            productResponse.setQuantity(x.getQuantity());
            productResponse.setPrice(x.getProductSize().getPrice());
            productResponse.setSize(x.getProductSize().getSize().getName());
            productResponse.setCategory(x.getProductSize().getProduct().getCategory().getName());

            productResponses.add(productResponse);
        }
        response.setProducts(productResponses);


        return response;
    }

    @Override
    public List<OrderProductDTO> getAllByOrderId(Long orderId) {
        return mapper.toDtos(rep.findAllByOrderId(orderId), context);
    }

    @Override
    public List<OrderProductDTO> getByOrderId(Long orderId) {
        return mapper.toDtos(rep.findByOrderId(orderId), context);
    }




}
