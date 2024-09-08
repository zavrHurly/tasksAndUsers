package com.example.tasksandusers.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "name", nullable = false, length = 100)
    val name: String,
    @Column(name = "password", length = 100)
    val password: String,
    @Column(name = "email", nullable = false, length = 100)
    val email: String,
)