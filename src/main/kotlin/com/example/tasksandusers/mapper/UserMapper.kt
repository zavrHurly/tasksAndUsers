package com.example.tasksandusers.mapper

import com.example.tasksandusers.model.dto.CreateUserRequestDTO
import com.example.tasksandusers.model.dto.UserDTO
import com.example.tasksandusers.model.entity.User

interface UserMapper {
    fun toEntity(dto: CreateUserRequestDTO): User
    fun toDTO(user: User): UserDTO
}