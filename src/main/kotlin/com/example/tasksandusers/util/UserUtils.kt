package com.example.tasksandusers.util

import com.example.tasksandusers.exception.ServiceException
import com.example.tasksandusers.jwt.CustomUserDetails
import com.example.tasksandusers.model.entity.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

class UserUtils {

    companion object {

        fun getCurrentUser(): User {
            val auth = SecurityContextHolder.getContext().authentication;
            if (auth is UsernamePasswordAuthenticationToken) {
                val customUserDetails = auth.principal as CustomUserDetails
                return customUserDetails.getUser()
            } else {
                throw ServiceException("No current user session")
            }
        }

        fun getCurrentUserId(): Long {
            val user = getCurrentUser()
            return if (user.id != null) {
                user.id!!
            } else {
                throw ServiceException("No current user id")
            }
        }
    }
}