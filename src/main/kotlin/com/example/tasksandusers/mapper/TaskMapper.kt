package com.example.tasksandusers.mapper

import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User

interface TaskMapper {
    fun taskDTOToTask(taskDTO: TaskDTO?, userToSave: User): Task
    fun toDTO(task: Task?): TaskDTO
    fun updateTask(taskDTO: TaskDTO, task: Task?): Task
    fun changeUser(task: Task, userToChange: User): Task
}