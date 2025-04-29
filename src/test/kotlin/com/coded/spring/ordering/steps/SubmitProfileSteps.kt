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

class SubmitProfileSteps {

    @Autowired
    lateinit var jwtService: JwtService

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    lateinit var requestEntity: HttpEntity<ProfileRequest>
    lateinit var response: ResponseEntity<String>

    @Given("I have a profile with first name {string}, last name {string}, and phone number {string}")
    fun i_have_a_profile(firstName: String, lastName: String, phoneNumber: String) {
        val profileRequest = ProfileRequest(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(jwtService.generateToken("GlobalUser"))

        requestEntity = HttpEntity(profileRequest, headers)
    }

    @When("I submit the profile to {string}")
    fun i_submit_the_profile(url: String) {
        response = restTemplate.postForEntity(url, requestEntity, String::class.java)
    }

    @Then("I should receive a 200 response for submitting profile")
    fun i_should_receive_200_response_for_profile() {
        assertEquals(200, response.statusCode.value())
    }
}