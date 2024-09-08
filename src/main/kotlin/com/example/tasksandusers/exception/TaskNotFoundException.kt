package com.example.tasksandusers.exception

import org.springframework.http.HttpStatus

class TaskNotFoundException(httpStatus: HttpStatus, message: String): Exception ()