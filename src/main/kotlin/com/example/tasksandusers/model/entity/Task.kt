package com.example.tasksandusers.model.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "task")
open class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,
    @Column(name = "name", nullable = false, length = 100)
    open var name: String,
    @Column(name = "description", length = 1000)
    open var description: String,
    @Column(name = "date")
    open var date: Instant,
    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    open var user: User,
)
