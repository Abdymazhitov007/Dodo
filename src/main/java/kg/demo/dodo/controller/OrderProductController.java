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

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderCreateRequest request, @RequestHeader String token, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(request, token, lang));
    }

    @GetMapping("/order-story")
    public ResponseEntity<?> getOrderStory(@RequestHeader String token, @RequestParam int pageNum, @RequestParam int pageSize, @RequestHeader int lang) {
        return ResponseEntity.ok(service.getOrderStory(token, pageNum, pageSize, lang));
    }

    @PostMapping("/repeat-order")
    public ResponseEntity<?> repeatOrder(@RequestHeader String token, @RequestParam Long orderId, @RequestHeader int lang) {
        return ResponseEntity.ok(service.repeatOrder(token, orderId, lang));
    }
}
