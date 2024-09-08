package com.example.tasksandusers.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "task")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "name", nullable = false, length = 100)
    val name: String,
    @Column(name = "description", length = 1000)
    val description: String,
    @Column(name = "date")
    val date: LocalDate,
    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,
)
