package kg.demo.dodo.controller;

import kg.demo.dodo.model.requests.AddressCreateRequest;
import kg.demo.dodo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AddressCreateRequest request,
                                    @RequestHeader String accessToken,
                                    @RequestHeader int lang) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, accessToken, lang));
    }

    @GetMapping("/get-address")
    public ResponseEntity<?> getByUserId(@RequestHeader String accessToken, @RequestHeader int lang) {
        return ResponseEntity.ok(service.getAllByUserId(accessToken, lang));
    }


}
