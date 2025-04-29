package com.coded.spring.ordering.steps

import com.coded.spring.ordering.DTO.SubmitItemRequest
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals
import com.coded.spring.ordering.authentication.jwt.JwtService


class SubmitItemSteps {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var jwtService: JwtService

    lateinit var requestEntity: HttpEntity<SubmitItemRequest>
    lateinit var response: ResponseEntity<String>

    @Given("I have an item with name {string}, quantity {int}, note {string}, and price {double}")
    fun i_have_an_item(name: String, quantity: Int, note: String, price: Double) {
        val itemRequest = SubmitItemRequest(
            name = name,
            quantity = quantity.toLong(),
            note = note,
            price = price
        )
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(jwtService.generateToken("GlobalUser")) // ✅ JWT Injection here
        requestEntity = HttpEntity(itemRequest, headers)
    }

    @When("I submit an item to endpoint {string}")
    fun i_send_post_request(url: String) {
        response = restTemplate.postForEntity(url, requestEntity, String::class.java)
    }

    @Then("I should receive a 200 response for submitting item")
    fun i_should_receive_success() {
        assertEquals(200, response.statusCode.value())
    }
}