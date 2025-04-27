package com.coded.spring.ordering.users
import com.coded.spring.ordering.DTO.UserRequest
import com.coded.spring.ordering.DTO.UserResponse
import com.coded.spring.ordering.exceptions.TransferFundsException
import jakarta.inject.Named
import org.springframework.stereotype.Service

const val USERNAME_MIN_LENGTH = 4
const val USERNAME_MAX_LENGTH = 30
const val PASSWORD_MIN_LENGTH = 9
const val PASSWORD_MAX_LENGTH = 30

@Named
@Service
class UsersService(
    private val usersRepository: UsersRepository,
) {

    fun registerUser(request: UserRequest): UserResponse {

        if (request.username.length < USERNAME_MIN_LENGTH ||
            request.username.length > USERNAME_MAX_LENGTH) {
            throw TransferFundsException(
                "Username must be between ${USERNAME_MIN_LENGTH} and ${USERNAME_MAX_LENGTH} characters")
        }

        if (request.password.length < PASSWORD_MIN_LENGTH ||
            request.password.length > PASSWORD_MAX_LENGTH) {
            throw TransferFundsException(
                "Password must be between ${PASSWORD_MIN_LENGTH} and ${PASSWORD_MAX_LENGTH} characters")
        }

        val createUser = UserEntity(
            name = request.name,
            age = request.age,
            username = request.username,
            password = request.password
        )

        val savedUser = usersRepository.save(createUser)
        return UserResponse(id = savedUser.id!!, username = savedUser.username)
    }

    fun listUsers(): List<UserRequest> = usersRepository.findAll().map {
        UserRequest(
            name = it.name,
            age = it.age,
            username = it.username,
            password = it.password

        )
    }
}