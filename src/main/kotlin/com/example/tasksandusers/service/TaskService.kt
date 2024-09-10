package com.example.tasksandusers.service

import com.example.tasksandusers.model.dto.TaskDTO

interface TaskService {
    fun getAllTasks(): List<TaskDTO>
    fun getTaskById(taskId: Long): TaskDTO
    fun createTask(task: TaskDTO): Long?
    fun updateTask(taskId: Long, task: TaskDTO): TaskDTO
    fun deleteTaskById(taskId: Long)
    fun changeUser(taskId: Long, userId: Long): TaskDTO
}