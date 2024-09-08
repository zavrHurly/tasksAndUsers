package com.example.tasksandusers.model.dto

import java.time.LocalDate


class TaskDTO(
    val id: Long,
    val name: String,
    val description: String,
    val date: LocalDate,
    val userId : Long,
)