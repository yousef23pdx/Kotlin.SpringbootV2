package com.coded.spring.ordering.steps

import com.coded.spring.ordering.authentication.jwt.JwtService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class ListItemsSteps {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var jwtService: JwtService

    lateinit var response: ResponseEntity<String>

    @When("I send a GET request to {string}")
    fun i_send_a_get_request_to(url: String) {
        val headers = HttpHeaders()
        headers.setBearerAuth(jwtService.generateToken("GlobalUser")) // ✅ JWT Token injected
        val entity = HttpEntity<String>(headers)

        response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
    }

    @Then("I should receive a {int} response for listing items")
    fun i_should_receive_a_response_for_listing_items(status: Int) {
        assertEquals(status, response.statusCode.value())
    }
}