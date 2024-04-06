package kg.demo.dodo.controller;


import jakarta.validation.Valid;
import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;
import kg.demo.dodo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> auth(@Valid @RequestBody AuthRequest request, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.auth(request, lang));
    }

    @GetMapping("/check")
    public ResponseEntity<?> validate(@RequestBody ValidateEmailReq emailReq, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.validate(emailReq, lang));
    }

    @GetMapping("get-id")
    public ResponseEntity<?> getIdByToken(@RequestHeader String token, @RequestHeader int lang) {
        return ResponseEntity.ok(authService.getUserIdByToken(token, lang));
    }
}
