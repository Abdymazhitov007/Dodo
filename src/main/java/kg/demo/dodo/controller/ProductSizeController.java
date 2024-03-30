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

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductCreateRequest request, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(request, lang));
    }

    @GetMapping
    public ResponseEntity<?> getProductSizeList() {
        return ResponseEntity.ok(service.getProductSizeList());
    }

}
