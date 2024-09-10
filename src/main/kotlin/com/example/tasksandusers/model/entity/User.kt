package com.example.tasksandusers.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,
    @Column(name = "name", nullable = false, length = 100)
    open var name: String,
    @Column(name = "password", length = 100)
    open var password: String,
    @Column(name = "email", nullable = false, length = 100)
    open var email: String,
    @OneToMany(cascade = [(CascadeType.MERGE)])
    open var tasks: MutableSet<Task> = mutableSetOf()
)