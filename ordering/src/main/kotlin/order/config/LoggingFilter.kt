package order.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

@Component
class LoggingFilter : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val wrappedRequest = ContentCachingRequestWrapper(request)
        val wrappedResponse = ContentCachingResponseWrapper(response)

        filterChain.doFilter(wrappedRequest, wrappedResponse)

        val requestBody = String(wrappedRequest.contentAsByteArray)
        logger.info("Request: method=${request.method}, uri=${request.requestURI}, body=$requestBody")

        val responseBody = String(wrappedResponse.contentAsByteArray)
        logger.info("Response: status=${response.status}, body=$responseBody")

        wrappedResponse.copyBodyToResponse()
    }
}