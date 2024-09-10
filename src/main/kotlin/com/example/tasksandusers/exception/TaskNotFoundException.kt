package com.example.tasksandusers.exception

class TaskNotFoundException(taskId: Long) : Exception("Задача с id $taskId не найдена")