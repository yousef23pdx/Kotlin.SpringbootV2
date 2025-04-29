package com.coded.spring.ordering.steps

import com.coded.spring.ordering.authentication.jwt.JwtService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class SubmitOrderStepDefinitions {

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    lateinit var postResponse: ResponseEntity<String>

    @When("I submit an order with itemIds to {string}")
    fun i_submit_order(url: String) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(jwtService.generateToken("GlobalUser"))

        val orderPayload = """
            {
              "itemIds": [1, 2]
            }
        """.trimIndent()

        val request = HttpEntity(orderPayload, headers)
        postResponse = restTemplate.postForEntity(url, request, String::class.java)
    }

    @Then("I should receive a 200 response for order submission")
    fun i_should_receive_200_response_for_submission() {
        assertEquals(200, postResponse.statusCode.value())
    }
}