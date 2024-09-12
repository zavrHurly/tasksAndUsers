package com.example.tasksandusers.controller

import com.example.tasksandusers.model.dto.CreateUserRequestDTO
import com.example.tasksandusers.model.dto.LoginRequestDTO
import com.example.tasksandusers.model.dto.LoginResponseDTO
import com.example.tasksandusers.model.dto.UserDTO
import com.example.tasksandusers.service.impl.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserServiceImpl) {

    @GetMapping("/users")
    fun getAllUsers(): List<UserDTO> = userService.getAllUsers()

    @PostMapping("/registration")
    fun createUser(@RequestBody userRequest: CreateUserRequestDTO): ResponseEntity<String> {
        val userId = userService.createUser(userRequest)
        return ResponseEntity.ok().body("User with ID $userId has been created")
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        return ResponseEntity.ok().body(userService.login(request))
    }

}