package com.example.tasksandusers.mapper

import com.example.tasksandusers.model.dto.UserDTO
import com.example.tasksandusers.model.entity.User
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper
interface UserMapper {
    fun toUser(userDTO: UserDTO?): User
    fun toDTO(user: User?): UserDTO
    fun updateUser(dto: UserDTO?, @MappingTarget user: User?): User
}