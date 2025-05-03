package ordering.client

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

data class CheckTokenResponse(val userId: Long)

@Component
class AuthenticationClient {

    private val restTemplate = RestTemplate()
    private val authServerUrl = "http://localhost:8081/auth/check-token"

    fun checkToken(token: String): CheckTokenResponse {
        val headers = HttpHeaders()
        headers.setBearerAuth(token)
        val requestEntity = HttpEntity<String>(headers)

        val response = restTemplate.exchange(
            authServerUrl,
            HttpMethod.POST,
            requestEntity,
            object : ParameterizedTypeReference<CheckTokenResponse>() {}
        )

        if (response.statusCode != HttpStatus.OK) {
            throw IllegalStateException("Invalid token")
        }

        return response.body ?: throw IllegalStateException("Missing response body from auth service")
    }
}