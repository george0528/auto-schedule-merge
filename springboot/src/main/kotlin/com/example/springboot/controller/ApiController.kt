package com.example.springboot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ApiController {
    @GetMapping("")
    fun index(): String {
        return "test"
    }
}