package com.coded.spring.ordering.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class UserRegistrationSteps {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>
    private lateinit var requestEntity: HttpEntity<String>

    @Given("I have a user with name {string}, age {int}, username {string}, and password {string}")
    fun i_have_a_user_payload(name: String, age: Int, username: String, password: String) {
        val jsonPayload = """
        {
          "name": "$name",
          "age": $age,
          "username": "$username",
          "password": "$password"
        }
    """.trimIndent()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        requestEntity = HttpEntity(jsonPayload, headers)
    }

    @When("I send a POST request to {string}")
    fun i_send_post_request(url: String) {
        response = restTemplate.postForEntity(url, requestEntity, String::class.java)
    }

    @Then("I should receive a {int} response")
    fun i_should_receive_http_code(httpCode: Int) {
        assertEquals(httpCode, response.statusCode.value())
    }

}