package com.example.tasksandusers.service.impl

import com.example.tasksandusers.jwt.CustomUserDetails
import com.example.tasksandusers.model.entity.User
import com.example.tasksandusers.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails? {
        val user: User? = userRepository.findByEmail(email)
        if (user != null) {
            return CustomUserDetails(user)
        }
        throw UsernameNotFoundException("Could not find user")
    }
}