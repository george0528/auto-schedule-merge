package com.example.springboot.repository

import com.example.springboot.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>