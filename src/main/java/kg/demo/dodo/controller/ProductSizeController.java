package kg.demo.dodo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.requests.ProductSizeCreateRequest;
import kg.demo.dodo.service.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/api/v1/product-size")
@RequiredArgsConstructor
@Tag(name="Продукты", description="Управление продуктами")
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    @Operation(summary = "Создание продукта", description = "Создает новый продукт на основе переданных данных.")
    public ResponseEntity<?> create(@ModelAttribute ProductCreateRequest request,
                                    @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, lang));
    }

    @PostMapping("/add-size")
    @Operation(summary = "Добавление размера к продукту", description = "Добавляет размер к существующему продукту.")
    public ResponseEntity<?> addSize(@RequestBody ProductSizeCreateRequest request,
                                     @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSize(request, lang));
    }


    @GetMapping("/category")
    @Operation(summary = "Получение продуктов по категории", description = "Получает список продуктов по указанной категории.")
    public ResponseEntity<?> getProductByCategory(@Parameter(description = "Идентификатор категории", required = true) @RequestParam Long categoryId,
                                                  @Parameter(description = "Номер страницы", required = true) @RequestParam int pageNum,
                                                  @Parameter(description = "Размер страницы", required = true) @RequestParam int pageSize,
                                                  @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(service.getProductByCategory(categoryId, pageNum, pageSize));
    }

    @GetMapping("/info")
    @Operation(summary = "Получение информации о продукте", description = "Получает полную информацию о продукте по его идентификатору.")
    public ResponseEntity<?> getProductInfo(@Parameter(description = "Идентификатор продукта", required = true) @RequestParam Long productId,
                                            @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(service.getFullInfoByProductId(productId, lang));
    }

    @GetMapping("/filter")
    @Operation(summary = "Фильтрация продуктов", description = "Фильтрует продукты по различным параметрам.")
    public ResponseEntity<?> getProductByFilter(@Parameter(description = "Идентификатор размера", required = false) @RequestParam(required = false) Long sizeId,
                                                @Parameter(description = "Минимальная цена", required = false) @RequestParam(required = false) Double fromPrice,
                                                @Parameter(description = "Максимальная цена", required = false) @RequestParam(required = false) Double toPrice,
                                                @Parameter(description = "Название продукта", required = false) @RequestParam(required = false) String name,
                                                @Parameter(description = "Идентификатор категории", required = false) @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(service.filter(sizeId, fromPrice, toPrice, name, categoryId));
    }

}




