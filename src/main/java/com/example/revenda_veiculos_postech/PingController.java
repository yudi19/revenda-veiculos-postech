package com.example.revenda_veiculos_postech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "revenda-api 2025.12.27-03";
    }
}
