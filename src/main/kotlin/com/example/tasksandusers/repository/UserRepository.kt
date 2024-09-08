package com.example.tasksandusers.repository

import com.example.tasksandusers.model.entity.Task
import com.example.tasksandusers.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{}