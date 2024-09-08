package com.example.tasksandusers.controller

import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User
import com.example.tasksandusers.service.TaskService
import com.example.tasksandusers.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/tasks")
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable("id") userId: Long): Optional<User> =
        userService.getUserById(userId)

    @PostMapping("/tasks")
    fun createEmployee(@RequestBody payload: User): User = userService.createUser(payload)

    @PutMapping("/tasks/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody payload: User): User =
        userService.updateUser(userId, payload)

    @DeleteMapping("/tasks/{id}")
    fun deleteTask(@PathVariable("id") userId: Long) = userService.deleteUserById(userId)

}