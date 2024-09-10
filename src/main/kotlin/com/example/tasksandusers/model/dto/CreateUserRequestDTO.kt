package com.example.tasksandusers.model.dto

data class CreateUserRequestDTO(
    val name: String,
    val email: String,
    val password: String
)