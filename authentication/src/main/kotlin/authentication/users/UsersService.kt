package authentication.users

import jakarta.inject.Named
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

const val USERNAME_MIN_LENGTH = 4
const val USERNAME_MAX_LENGTH = 30
const val PASSWORD_MIN_LENGTH = 9
const val PASSWORD_MAX_LENGTH = 30

@Named
@Service
class UsersService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registerUser(request: UserRequest): UserResponse {
        if (request.username.length !in USERNAME_MIN_LENGTH..USERNAME_MAX_LENGTH) {
            throw IllegalArgumentException("Username must be between $USERNAME_MIN_LENGTH and $USERNAME_MAX_LENGTH characters")
        }

        if (request.password.length !in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH) {
            throw IllegalArgumentException("Password must be between $PASSWORD_MIN_LENGTH and $PASSWORD_MAX_LENGTH characters")
        }

        val user = UserEntity(
            name = request.name,
            age = request.age,
            username = request.username,
            password = passwordEncoder.encode(request.password)
        )

        val saved = usersRepository.save(user)
        return UserResponse(id = saved.id!!, username = saved.username)
    }

    fun findByUsername(username: String): UserEntity {
        return usersRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found for username: $username")
    }

    fun listUsers(): List<UserResponse> {
        return usersRepository.findAll().map {
            UserResponse(
                id = it.id ?: 0,
                username = it.username
            )
        }
    }
}