package com.example.tasksandusers.mapper.impl

import com.example.tasksandusers.exception.ServiceException
import com.example.tasksandusers.mapper.UserMapper
import com.example.tasksandusers.model.dto.CreateUserRequestDTO
import com.example.tasksandusers.model.dto.UserDTO
import com.example.tasksandusers.model.entity.User
import com.example.tasksandusers.util.EmailValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapperImpl(val passwordEncoder: PasswordEncoder) : UserMapper {

    override fun toEntity(dto: CreateUserRequestDTO): User {
        return if (dto.name.isNotEmpty() && dto.password.isNotEmpty() && EmailValidator.isValid(dto.email)) {
            User(
                id = null,
                name = dto.name,
                password = passwordEncoder.encode(dto.password),
                email = dto.email,
            )
        } else {
            throw ServiceException("Одно или несколько полей указаны некорретно")
        }
    }

    override fun toDTO(user: User): UserDTO {
        return UserDTO(
            id = user.id!!,
            name = user.name,
            password = user.password,
            email = user.email,
        )
    }
}