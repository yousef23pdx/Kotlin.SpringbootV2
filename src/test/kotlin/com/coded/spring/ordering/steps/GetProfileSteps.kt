package com.coded.spring.ordering.steps

import com.coded.spring.ordering.DTO.ProfileRequest
import com.coded.spring.ordering.authentication.jwt.JwtService
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.assertEquals

class GetProfileSteps {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var jwtService: JwtService

    lateinit var headers: HttpHeaders
    lateinit var response: ResponseEntity<String>

    @Given("A profile is already created for user {string}")
    fun a_profile_is_already_created(username: String) {
        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(jwtService.generateToken(username))

        val profileRequest = ProfileRequest(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "12345678"
        )

        val requestEntity = HttpEntity(profileRequest, headers)
        restTemplate.postForEntity("/profile", requestEntity, String::class.java)
    }

    @When("I send an authenticated GET request to {string} to retrieve profile")
    fun i_send_authenticated_get_request(url: String) {
        val entity = HttpEntity<String>(headers)
        response = restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
    }

    @Then("I should receive a 200 response for retrieving profile")
    fun i_should_receive_200_response_for_retrieving_profile() {
        assertEquals(200, response.statusCode.value())
    }
}