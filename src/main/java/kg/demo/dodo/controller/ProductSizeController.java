package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.model.requests.ProductSizeCreateRequest;
import kg.demo.dodo.service.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-size")
@RequiredArgsConstructor
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductCreateRequest request, @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, lang));
    }

    @PostMapping("/add-size")
    public ResponseEntity<?> addSize(@RequestBody ProductSizeCreateRequest request, @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addSize(request, lang));
    }


    @GetMapping("/category")
    public ResponseEntity<?> getProductByCategory(@RequestParam Long categoryId,
                                                  @RequestParam int pageNum,
                                                  @RequestParam int pageSize,
                                                  @RequestHeader int lang) {
        return ResponseEntity.ok(service.getProductByCategory(categoryId, pageNum, pageSize));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getProductInfo(@RequestParam Long productId,
                                            @RequestHeader int lang) {
        return ResponseEntity.ok(service.getFullInfoByProductId(productId, lang));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getProductByFilter(@RequestParam(required = false) Long sizeId,
                                                @RequestParam(required = false) Double fromPrice,
                                                @RequestParam(required = false) Double toPrice,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(service.filter(sizeId, fromPrice, toPrice, name, categoryId));
    }

}




