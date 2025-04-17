package com.coded.spring.ordering

import com.coded.spring.ordering.users.UserEntity
import com.coded.spring.ordering.users.UsersRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class InitUserRunner {
    @Bean
    fun initUsers(userRepository: UsersRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val user = UserEntity(
            name = "Ahmed",
            username = "Ahmed123",
            password = passwordEncoder.encode("password123"),
            age = 22,
        )
        if (userRepository.findByUsername(user.username) == null) {
            println("Creating user ${user.username}")
            userRepository.save(user)
        } else  {
            println("User ${user.username} already exists")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<InitUserRunner>(*args).close()
}