package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.AddressCreateRequest;
import kg.demo.dodo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddressCreateRequest request, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(request, lang));
    }

    @GetMapping
    public ResponseEntity<?> getByUserId(@RequestHeader String token) {
        return ResponseEntity.ok(service.getByUserId(token));
    }


}
