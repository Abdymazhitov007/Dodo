package kg.demo.dodo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.model.requests.AddressCreateRequest;
import kg.demo.dodo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@Tag(name="Адрес", description="Управление адресами")
public class AddressController {

    private final AddressService service;

    @PostMapping("/create")
    @Operation(summary = "Создание адреса", description = "Создает новый адрес на основе переданных данных.")
    public ResponseEntity<?> create(@RequestBody AddressCreateRequest request,
                                    @RequestHeader(name = "accessToken") @Parameter(description = "Токен доступа", required = true) String accessToken,
                                    @RequestHeader @Parameter(description = "Язык", required = true) int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, accessToken, lang));
    }

    @GetMapping("/get-address")
    @Operation(summary = "Получение адресов пользователя", description = "Получает список всех адресов пользователя.")
    public ResponseEntity<?> getByUserId(@RequestHeader(name = "accessToken") @Parameter(description = "Токен доступа", required = true) String accessToken,
                                         @RequestHeader @Parameter(description = "Язык", required = true) int lang) {
        return ResponseEntity.ok(service.getAllByToken(accessToken, lang));
    }


}
