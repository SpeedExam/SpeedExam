package com.ensat.speedexam.Controllers;
import com.ensat.speedexam.Services.PersonService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    @GetMapping("/Hello")
    public ResponseEntity<?> sayHello(){
        return  ResponseEntity.ok("Hello");
    }


}
