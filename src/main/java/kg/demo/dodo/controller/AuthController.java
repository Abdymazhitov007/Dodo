package kg.demo.dodo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;
import kg.demo.dodo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name="Аутентификация", description="Управление аутентификацией")
public class AuthController {
    private final AuthService authService;

    @PostMapping
    @Operation(summary = "Авторизация пользователя", description = "Авторизует пользователя на основе предоставленных учетных данных.")
    public ResponseEntity<?> auth(@RequestBody AuthRequest request,
                                  @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(authService.auth(request, lang));
    }

    @PostMapping("/check")
    @Operation(summary = "Проверка электронной почты", description = "Проверяет сгенерированный временный пароль и выдает токен.")
    public ResponseEntity<?> validate(@RequestBody ValidateEmailReq emailReq,
                                      @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(authService.validate(emailReq, lang));
    }

    @GetMapping("/user-info")
    @Operation(summary = "Получение информации о пользователе", description = "Получает информацию о пользователе на основе предоставленного токена.")
    public ResponseEntity<?> getUserInfoByToken(@Parameter(description = "Токен доступа", required = true) @RequestHeader String accessToken,
                                                @Parameter(description = "Язык", required = true) @RequestHeader int lang) {
        return ResponseEntity.ok(authService.getUserInfoByToken(accessToken, lang));
    }
}
