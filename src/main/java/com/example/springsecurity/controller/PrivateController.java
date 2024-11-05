package com.example.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
@RequiredArgsConstructor
public class PrivateController {

    @GetMapping
    public String helloWorld() {
        return "Hello World from PRIVATE endpoint";
    }
}
