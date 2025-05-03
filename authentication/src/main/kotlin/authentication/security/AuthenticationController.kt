package authentication.security

import authentication.jwt.JwtService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.authentication.*
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Tag(name = "AUTHENTICATION", description = "Endpoints related to user login and token validation")
@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {

    @Operation(
        summary = "Login and generate JWT token",
        description = "Accepts username and password and returns a JWT token upon successful authentication"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successfully authenticated",
                content = [Content(schema = Schema(implementation = AuthenticationResponse::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid username or password",
                content = [Content()]
            )
        ]
    )
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        val authentication = authenticationManager.authenticate(authToken)

        if (authentication.isAuthenticated) {
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            val token = jwtService.generateToken(userDetails.username)
            return AuthenticationResponse(token)
        } else {
            throw UsernameNotFoundException("Invalid user request!")
        }
    }

    @Operation(
        summary = "Check token validity",
        description = "Validates the JWT token provided in the Authorization header"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Token is valid"),
            ApiResponse(responseCode = "401", description = "Invalid or expired token")
        ]
    )
    @PostMapping("/check-token")
    fun checkToken(principal: Principal): TokenCheckResponse {
        return TokenCheckResponse(username = principal.name)
    }
}

data class AuthenticationRequest(
    val username: String,
    val password: String
)

data class AuthenticationResponse(
    val token: String
)

data class TokenCheckResponse(
    val username: String
)