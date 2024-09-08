package com.example.tasksandusers.controller

import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.service.TaskService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TaskController(private val taskService: TaskService) {

    @GetMapping("/tasks")
    fun getAllTasks(): List<Task> = taskService.getAllTasks()

    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable("id") taskId: Long): TaskDTO =
        taskService.getTaskById(taskId)

    @PostMapping("/tasks")
    fun createEmployee(@RequestBody payload: TaskDTO): Long? = taskService.createTask(payload)

    @PutMapping("/tasks/{id}")
    fun updateTaskById(@PathVariable("id") taskId: Long, @RequestBody payload: TaskDTO): TaskDTO =
        taskService.updateTask(taskId, payload)

    @DeleteMapping("/tasks/{id}")
    fun deleteTask(@PathVariable("id") taskId: Long) = taskService.deleteTaskById(taskId)

}