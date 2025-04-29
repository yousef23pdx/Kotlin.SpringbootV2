package com.coded.spring.ordering.steps

import com.coded.spring.ordering.authentication.jwt.JwtService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class ListOrdersStepDefinitions {

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    lateinit var getResponse: ResponseEntity<String>

    @When("I request my orders from {string}")
    fun i_request_orders(url: String) {
        val headers = HttpHeaders()
        headers.setBearerAuth(jwtService.generateToken("GlobalUser"))
        val entity = HttpEntity<String>(headers)

        getResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
    }

    @Then("I should receive a 200 response with my orders")
    fun i_should_receive_orders_response() {
        assertEquals(200, getResponse.statusCode.value())
    }
}