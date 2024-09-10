package com.example.tasksandusers.repository

import com.example.tasksandusers.model.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {

    fun findByUserId(userId: Long): List<Task>

    fun getByIdAndUserId(taskId: Long, userId: Long): Task?
}