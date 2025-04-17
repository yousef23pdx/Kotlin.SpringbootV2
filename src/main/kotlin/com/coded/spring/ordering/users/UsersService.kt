package com.coded.spring.ordering.users
import com.coded.spring.ordering.DTO.User
import jakarta.inject.Named

@Named
class UsersService(
    private val usersRepository: UsersRepository,
) {

    fun listUsers(): List<User> = usersRepository.findAll().map {
        User(
            name = it.name,
            age = it.age,
            username = it.username,
            password = it.password

        )
    }
}