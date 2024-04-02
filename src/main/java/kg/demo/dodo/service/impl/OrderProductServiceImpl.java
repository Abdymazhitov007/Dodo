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
    private final ProductService productService;


    public OrderProductServiceImpl(OrderProductRep rep, OrderProductMapper mapper, AuthService authService, OrderService orderService, UserService userService, ProductSizeService productSizeService, AddressService addressService, ProductService productService) {
        super(rep, mapper);
        this.authService = authService;
        this.orderService = orderService;
        this.userService = userService;
        this.productSizeService = productSizeService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @Override
    public String create(OrderCreateRequest request, String token, int lang) {

        UserDTO user = userService.findById(authService.getUserIdByToken(token));
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
            totalPrice += product.getPrice();
        }

        order.setTotalPrice(totalPrice);
        order.setDodoCoins((totalPrice - order.getDiscount()) / 10);

        user.setDodoCoins(user.getDodoCoins() - discount + order.getDodoCoins());


        userService.save(user);

        OrderDTO orderFromDB = orderService.save(order);

        for (ProductOrderList item : request.getProductOrderLists()) {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setOrder(orderFromDB);

            ProductSizeDTO product = productSizeService.findById(item.getProductSizeId());
            orderProductDTO.setProductSize(product);
            orderProductDTO.setPrice(item.getPrice());

            save(orderProductDTO);
        }


        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "orderPreparing");
    }

    @Override
    public List<OrderStoryResponse> getOrderStory(String token, int lang) {

        UserDTO userDTO = userService.findById(authService.getUserIdByToken(token));
        List<OrderStoryResponse> result = new ArrayList<>();

        for (OrderDTO item: orderService.getByUserId(userDTO.getId())) {

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
    public String repeatOrder(String token, RepeatOrderRequest request, int lang) {
        UserDTO userDTO = userService.findById(authService.getUserIdByToken(token));
        OrderDTO orderDTO = orderService.findById(request.getOrderId());

        if (!orderDTO.getUser().getId().equals(userDTO.getId())) {
            throw new RuntimeException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "wrongOrderId"));
        }
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setAddressId(request.getAddressId());
        orderCreateRequest.setPaymentType(request.getPaymentType());
        orderCreateRequest.setOrderDate(request.getOrderDate());

        List<ProductOrderList> productOrderLists = new ArrayList<>();
        for (OrderProductDTO item : getAllByOrderId(request.getOrderId())) {
            ProductOrderList productOrderList = new ProductOrderList();
            productOrderList.setPrice(item.getProductSize().getPrice());
            productOrderList.setProductSizeId(item.getProductSize().getId());
            productOrderLists.add(productOrderList);
        }
        orderCreateRequest.setProductOrderLists(productOrderLists);

        create(orderCreateRequest, token, lang);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "orderPreparing");
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
