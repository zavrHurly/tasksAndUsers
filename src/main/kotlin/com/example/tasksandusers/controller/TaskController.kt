package com.example.tasksandusers.controller

import com.example.tasksandusers.model.dto.TaskDTO
import com.example.tasksandusers.service.impl.TaskServiceImpl
import org.springframework.web.bind.annotation.*

@RestController
class TaskController(private val taskService: TaskServiceImpl) {

    @GetMapping("/tasks")
    fun getAllTasks(): List<TaskDTO> = taskService.getAllTasks()

    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable("id") taskId: Long): TaskDTO =
        taskService.getTaskById(taskId)

    @PostMapping("/tasks")
    fun createTask(@RequestBody payload: TaskDTO): Long? = taskService.createTask(payload)

    @PutMapping("/tasks/{id}")
    fun updateTaskById(@PathVariable("id") taskId: Long, @RequestBody payload: TaskDTO): TaskDTO =
        taskService.updateTask(taskId, payload)

    @DeleteMapping("/tasks/{id}")
    fun deleteTaskById(@PathVariable("id") taskId: Long) = taskService.deleteTaskById(taskId)

    @PostMapping("/tasks/{id}/user/{userId}")
    fun changeUser(@PathVariable("id") taskId: Long, @PathVariable("userId") userId: Long): TaskDTO =
        taskService.changeUser(taskId, userId)

}