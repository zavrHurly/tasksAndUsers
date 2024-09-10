package com.example.tasksandusers.service.impl

import com.example.tasksandusers.exception.ServiceException
import com.example.tasksandusers.jwt.CustomUserDetails
import com.example.tasksandusers.jwt.JwtUtil
import com.example.tasksandusers.mapper.impl.UserMapperImpl
import com.example.tasksandusers.model.dto.*
import com.example.tasksandusers.repository.UserRepository
import com.example.tasksandusers.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapperImpl,
    private val jwtUtil: JwtUtil,
    private val authManager: AuthenticationManager
) : UserService {
    // Получаем список всех пользователей
    override fun getAllUsers(): List<UserDTO> {
        val users = userRepository.findAll()
        return users.map { userMapper.toDTO(it) }
    }

    //Создаем пользователя
    override fun createUser(user: CreateUserRequestDTO): Long? {
        if (userRepository.existsByEmail(user.email)) {
            throw ServiceException("Недопустимый email")
        }
        return userRepository.save(userMapper.toEntity(user)).id
    }

    override fun login(request: LoginRequestDTO): LoginResponseDTO {
        try {
            val authentication: Authentication = authManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.email,
                    request.password
                )
            )

            val userDetails: CustomUserDetails = authentication.principal as CustomUserDetails
            val accessToken: String = jwtUtil.generateToken(userDetails.username)

            return LoginResponseDTO(userDetails.username, accessToken)
        } catch (ex: BadCredentialsException) {
            throw BadCredentialsException(ex.message)
        }
    }
}