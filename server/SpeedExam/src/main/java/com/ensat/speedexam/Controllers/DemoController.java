package com.ensat.speedexam.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-controller")
@RequiredArgsConstructor
public class DemoController {
    @GetMapping
    public ResponseEntity<String> Security(){

        return ResponseEntity.ok("Secured end point");
    }
}

