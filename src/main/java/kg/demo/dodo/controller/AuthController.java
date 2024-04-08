package kg.demo.dodo.controller;


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
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody AuthRequest request, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.auth(request, lang));
    }

    @PostMapping("/check")
    public ResponseEntity<?> validate(@RequestBody ValidateEmailReq emailReq, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.validate(emailReq, lang));
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfoByToken(@RequestHeader String accessToken, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.getUserInfoByToken(accessToken, lang));
    }
}
