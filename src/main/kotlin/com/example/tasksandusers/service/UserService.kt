package com.example.tasksandusers.service

import com.example.tasksandusers.exception.TaskNotFoundException
import com.example.tasksandusers.exception.UserNotFoundException
import com.example.tasksandusers.mapper.UserMapper
import com.example.tasksandusers.model.dto.UserDTO
import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User
import com.example.tasksandusers.repository.TaskRepository
import com.example.tasksandusers.repository.UserRepository
import org.mapstruct.Mapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository, private val userMapper: UserMapper) {
    // Получаем список всех пользователей
    fun getAllUsers(): List<User> = userRepository.findAll()

    //Получаем задачу по userId
    fun getUserById(userId: Long): UserDTO = userMapper.toDTO(userRepository.findById(userId).get())
        ?: throw UserNotFoundException(HttpStatus.NOT_FOUND, "Задача с $userId не найдена")

    //Создаем пользователя
    fun createUser(user: UserDTO): Long? {
        val result: User = userMapper.toUser(user)
        return userRepository.save(result).id
    }

    //Обновляем подьзователя, если он найден, если нет выбрасываем исключение
    fun updateUser(userId: Long, user: UserDTO): UserDTO {
        val userFromDB: User = userRepository.findById(userId).get();
        val result: User = userRepository.save(userMapper.updateUser(user, userFromDB))
        return userMapper.toDTO(result)
    }

    //Удаляем пользователя
    fun deleteUserById(userId: Long) {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
        } else throw TaskNotFoundException(HttpStatus.NOT_FOUND, "Пользователь с $userId не найдена")
    }
}