package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.model.requests.RepeatOrderRequest;
import kg.demo.dodo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderCreateRequest request, @RequestHeader String token, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(request, token, lang));
    }

    @GetMapping("/order-story")
    public ResponseEntity<?> getOrderStory(@RequestHeader String token, @RequestHeader int lang) {
        return ResponseEntity.ok(service.getOrderStory(token, lang));
    }

    @PostMapping("/repeat-order")
    public ResponseEntity<?> repeatOrder(@RequestHeader String token, @RequestBody RepeatOrderRequest request, @RequestHeader int lang) {
        return ResponseEntity.ok(service.repeatOrder(token, request, lang));
    }
}
