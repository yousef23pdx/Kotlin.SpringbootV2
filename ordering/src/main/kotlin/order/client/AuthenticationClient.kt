package order.client

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

data class CheckTokenResponse(val userId: Long)

@Component
class AuthenticationClient {

    private val restTemplate = RestTemplate()
    private val authServerUrl = "http://localhost:8081/auth/v1/check-token"

    fun checkToken(token: String): CheckTokenResponse {
        println("🔐 [AuthenticationClient] Checking token...")

        val headers = HttpHeaders()
        headers.setBearerAuth(token)
        val requestEntity = HttpEntity<String>(headers)

        println("📤 Sending request to auth server at $authServerUrl with token: $token")

        try {
            val response = restTemplate.exchange(
                authServerUrl,
                HttpMethod.POST,
                requestEntity,
                object : ParameterizedTypeReference<CheckTokenResponse>() {}
            )

            println("✅ Received response from auth server: ${response.statusCode}")
            println("📦 Response body: ${response.body}")

            if (response.statusCode != HttpStatus.OK) {
                throw IllegalStateException("❌ Invalid token, status: ${response.statusCode}")
            }

            return response.body ?: throw IllegalStateException("❌ Missing response body from auth service")

        } catch (ex: Exception) {
            println("🔥 Exception while calling auth server: ${ex.message}")
            throw IllegalStateException("Failed to validate token: ${ex.message}", ex)
        }
    }
}