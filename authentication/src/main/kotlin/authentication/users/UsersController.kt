package authentication.users

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "AUTHENTICATION", description = "Handles user registration and listing")
class UsersController(
    private val usersService: UsersService
) {

    @PostMapping("/v1/register")
    @Operation(
        summary = "Register a new user",
        description = "Registers a new user using username and password",
        responses = [
            ApiResponse(responseCode = "200", description = "User successfully registered"),
            ApiResponse(responseCode = "400", description = "Validation failed or user already exists")
        ]
    )
    fun registerUser(@RequestBody request: UserRequest): ResponseEntity<Any> {
        return try {
            val newUser = usersService.registerUser(request)
            ResponseEntity.ok(newUser)
        } catch (e: TransferFundsException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        }
    }

    @GetMapping("/v1/list")
    @Operation(
        summary = "List all users",
        description = "Returns a list of all registered users",
        responses = [
            ApiResponse(responseCode = "200", description = "Users listed successfully")
        ]
    )
    fun users() = usersService.listUsers()
}