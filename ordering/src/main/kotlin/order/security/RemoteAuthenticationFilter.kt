package order.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import order.client.AuthenticationClient
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
@Component
class RemoteAuthenticationFilter(
    private val authenticationClient: AuthenticationClient
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("✅ RemoteAuthenticationFilter START")

        val authHeader = request.getHeader("Authorization")
        println("🟨 Authorization Header: $authHeader")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            println("⚠️ No Bearer token found. Skipping auth.")
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.removePrefix("Bearer ").trim()
        println("🔑 Extracted Token: $token")

        try {
            val result = authenticationClient.checkToken(token)
            println("✅ Token validated. userId=${result.userId}")
            request.setAttribute("userId", result.userId)
        } catch (ex: Exception) {
            println("❌ Token validation failed: ${ex.message}")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token")
            return
        }

        println("➡️ Proceeding with filter chain")
        filterChain.doFilter(request, response)
    }
}