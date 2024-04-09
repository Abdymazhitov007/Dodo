package kg.demo.dodo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.model.requests.CategoryCreateRequest;
import kg.demo.dodo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name="Категории", description="Управление категориями")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    @Operation(summary = "Создание категории", description = "Создает новую категорию на основе переданных данных.")
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request,
                                    @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, lang));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Получение списка категорий", description = "Получает список всех категорий.")
    public ResponseEntity<?> getCategoryList() {
        return ResponseEntity.ok(service.getCategoryList());
    }

}
