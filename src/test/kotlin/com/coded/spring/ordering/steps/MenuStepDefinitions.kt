package com.coded.spring.ordering.steps

import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MenuStepDefinitions {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    private lateinit var response: ResponseEntity<String>

    @When("I request the menu")
    fun iRequestTheMenu() {
        response = restTemplate.getForEntity("/menu/v1/list", String::class.java)
    }

    @Then("I should receive a list of menu items")
    fun iShouldReceiveMenuItems() {
        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        println("✅ Menu Response: ${response.body}")
    }
}