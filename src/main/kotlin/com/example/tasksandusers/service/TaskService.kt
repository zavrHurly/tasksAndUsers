package com.example.tasksandusers.service

import com.example.tasksandusers.model.dto.TaskDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TaskService {
    fun getAllTasks(pageable: Pageable): Page<TaskDTO>
    fun getTaskById(taskId: Long): TaskDTO
    fun createTask(task: TaskDTO): Long?
    fun updateTask(taskId: Long, task: TaskDTO): TaskDTO
    fun deleteTaskById(taskId: Long)
    fun changeUser(taskId: Long, userId: Long): TaskDTO
}