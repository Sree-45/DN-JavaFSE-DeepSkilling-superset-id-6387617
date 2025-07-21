package com.cognizant.greet_service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GreetController {

    @GetMapping("/greet")
    public String sayHello() {
        return "hello world!";
    }
    
}
