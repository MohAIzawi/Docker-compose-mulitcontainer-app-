package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api/your-endpoint")
public ResponseEntity<String> yourEndpointMethod() {
    return ResponseEntity.ok("Response from /api/your-endpoint");
}

}
