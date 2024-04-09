package kg.demo.dodo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.model.requests.OrderCreateRequest;
import kg.demo.dodo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-product")
@RequiredArgsConstructor
@Tag(name="Заказы", description="Управление заказами")
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping("/create")
    @Operation(summary = "Создание заказа", description = "Создает новый заказ на основе переданных данных.")
    public ResponseEntity<?> create(@RequestBody OrderCreateRequest request,
                                    @Parameter(description = "Токен доступа", required = true) @RequestHeader String accessToken,
                                    @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, accessToken, lang));
    }

    @GetMapping("/order-story")
    @Operation(summary = "Получение истории заказов", description = "Получает историю заказов для пользователя.")
    public ResponseEntity<?> getOrderStory(@Parameter(description = "Токен доступа", required = true) @RequestHeader String accessToken,
                                           @Parameter(description = "Номер страницы", required = true) @RequestParam int pageNum,
                                           @Parameter(description = "Размер страницы", required = true) @RequestParam int pageSize,
                                           @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(service.getOrderStory(accessToken, pageNum, pageSize, lang));
    }

    @PostMapping("/repeat-order")
    @Operation(summary = "Повторение заказа", description = "Повторяет ранее сделанный заказ.")
    public ResponseEntity<?> repeatOrder(@Parameter(description = "Токен доступа", required = true) @RequestHeader String accessToken,
                                         @Parameter(description = "Идентификатор заказа", required = true) @RequestParam Long orderId,
                                         @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(service.repeatOrder(accessToken, orderId, lang));
    }
}
