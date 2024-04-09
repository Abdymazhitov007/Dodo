package kg.demo.dodo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
@Tag(name="Размеры", description="Управление размерами")
public class SizeController {

    private final SizeService service;

    @PostMapping("/create")
    @Operation(summary = "Создание размера", description = "Создает новый размер на основе переданного названия.")
    public ResponseEntity<?> create(@Parameter(description = "Название размера", required = true) @RequestParam String name,
                                    @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(name, lang));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Получение списка всех размеров", description = "Получает список всех доступных размеров.")
    public ResponseEntity<?> getSizeList() {
        return ResponseEntity.ok(service.getSizeList());
    }

}
