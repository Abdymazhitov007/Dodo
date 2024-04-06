package kg.demo.dodo.controller;

import kg.demo.dodo.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam String name, @RequestHeader int lang) {
        return ResponseEntity.ok(service.create(name, lang));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getSizeList() {
        return ResponseEntity.ok(service.getSizeList());
    }

}
