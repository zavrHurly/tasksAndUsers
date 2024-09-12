package com.example.tasksandusers.service

import com.example.tasksandusers.model.dto.CreateUserRequestDTO
import com.example.tasksandusers.model.dto.LoginRequestDTO
import com.example.tasksandusers.model.dto.LoginResponseDTO
import com.example.tasksandusers.model.dto.UserDTO

interface UserService {
    fun getAllUsers(): List<UserDTO>
    fun createUser(user: CreateUserRequestDTO): Long
    fun login(request: LoginRequestDTO): LoginResponseDTO
}