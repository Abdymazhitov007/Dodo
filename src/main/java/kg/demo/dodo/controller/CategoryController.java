package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.CategoryCreateRequest;
import kg.demo.dodo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request, @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, lang));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getCategoryList() {
        return ResponseEntity.ok(service.getCategoryList());
    }

}
