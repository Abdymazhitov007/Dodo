package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product")
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderCreateRequest request,
                                    @RequestHeader String accessToken,
                                    @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, accessToken, lang));
    }

    @GetMapping("/order-story")
    public ResponseEntity<?> getOrderStory(@RequestHeader String accessToken,
                                           @RequestParam int pageNum,
                                           @RequestParam int pageSize,
                                           @RequestHeader int lang) {
        return ResponseEntity.ok(service.getOrderStory(accessToken, pageNum, pageSize, lang));
    }

    @PostMapping("/repeat-order")
    public ResponseEntity<?> repeatOrder(@RequestHeader String accessToken,
                                         @RequestParam Long orderId,
                                         @RequestHeader int lang) {
        return ResponseEntity.ok(service.repeatOrder(accessToken, orderId, lang));
    }
}
