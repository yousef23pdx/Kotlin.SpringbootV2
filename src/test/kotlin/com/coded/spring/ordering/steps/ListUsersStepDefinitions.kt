package com.coded.spring.ordering.steps

import com.coded.spring.ordering.authentication.jwt.JwtService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class ListUsersStepDefinitions {

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var restTemplate: TestRestTemplate
    lateinit var response: ResponseEntity<String>

    @When("I send an authenticated GET request to {string}")
    fun i_send_authenticated_get_request(url: String) {
        val headers = HttpHeaders()
        //GlobalToken.jwtToken?.let { headers.setBearerAuth(it) }
        headers.setBearerAuth(jwtService.generateToken("GlobalUser"))
        val entity = HttpEntity<String>(headers)

        response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
    }

    @Then("I should receive a 200 response for listing users")
    fun i_should_receive_200_response_for_listing_users() {
        assertEquals(200, response.statusCode.value())
    }
}