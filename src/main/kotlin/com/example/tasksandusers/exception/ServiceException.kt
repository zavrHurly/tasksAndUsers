package com.example.tasksandusers.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ServiceException(message: String) : RuntimeException(message) {
}