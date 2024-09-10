package com.example.tasksandusers.repository

import com.example.tasksandusers.model.entity.Task
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {

    fun findByUserId(userId: Long): List<Task>

    fun findByIdAndUserId(taskId: Long, userId: Long): Task?

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Task>
}