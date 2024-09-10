package com.example.tasksandusers.repository

import com.example.tasksandusers.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?

    fun findUserById(id: Long): User?
}