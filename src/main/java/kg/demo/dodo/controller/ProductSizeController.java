package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.ProductCreateRequest;
import kg.demo.dodo.service.ProductService;
import kg.demo.dodo.service.ProductSizeService;
import kg.demo.dodo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-size")
@RequiredArgsConstructor
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute ProductCreateRequest request, @RequestParam(required = false, defaultValue = "-1") Long productId, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(request, productId, lang));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getProductSizeList() {
        return ResponseEntity.ok(service.getProductSizeList());
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductByCategory(@RequestParam Long categoryId, @RequestParam int pageNum, @RequestParam int pageSize , @RequestHeader int lang) {
        return ResponseEntity.ok(service.getProductByCategory(categoryId, pageNum, pageSize));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getProductInfo(@RequestParam Long productId, @RequestHeader int lang) {
        return ResponseEntity.ok(service.getFullInfoByProductId(productId));
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




