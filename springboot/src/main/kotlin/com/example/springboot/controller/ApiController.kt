package com.example.springboot.controller

import com.example.springboot.entity.User
import com.example.springboot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var userRepository: UserRepository
    @GetMapping("index")
    fun showUsers(): Iterable<User> {
        val users = userRepository.findAll()
        return users
//        return "index"
    }
}