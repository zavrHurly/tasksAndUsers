package com.example.tasksandusers.exception

class UserNotFoundException(userId: Long) : Exception("Пользователь с id $userId не найдена")