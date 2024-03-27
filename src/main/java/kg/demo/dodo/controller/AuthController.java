package kg.demo.dodo.controller;


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
    ResponseEntity<?> auth(@RequestBody AuthRequest request ){
        return ResponseEntity.ok(authService.auth(request));
    }

    @GetMapping("/check")
    ResponseEntity<?> validate(@RequestBody ValidateEmailReq emailReq){
        return ResponseEntity.ok(authService.validate(emailReq));
    }



}
