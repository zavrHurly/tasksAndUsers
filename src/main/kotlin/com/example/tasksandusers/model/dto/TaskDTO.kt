package com.example.tasksandusers.model.dto

import java.time.Instant


class TaskDTO(
    val id: Long,
    var name: String,
    var description: String,
    var date: Instant
)