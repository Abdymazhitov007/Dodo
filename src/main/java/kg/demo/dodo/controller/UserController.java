package kg.demo.dodo.controller;

import kg.demo.dodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok(service.getUserList());
    }

}
