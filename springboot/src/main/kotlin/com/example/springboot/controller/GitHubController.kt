package com.example.springboot.controller

import com.example.springboot.repository.GitHubRepository
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
class GitHubController {
    @GetMapping("/callback")
    fun callback(@RequestParam("code") code: String): Any {
        val repository = GitHubRepository()
        try {
            val response = repository.getAccessToken(code)
            return response
        } catch (e: Exception) {
            System.out.println(e.message)
            return "error"
        }
    }
}