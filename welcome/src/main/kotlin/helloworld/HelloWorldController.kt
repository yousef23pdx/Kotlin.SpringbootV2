package com.coded.spring.ordering.helloworld

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "HOME_PAGE", description = "General welcome and homepage information")
class WelcomeController(
    @Value("\${company.name}") val companyName: String,
    @Value("\${festive-mode:false}") val festiveMode: Boolean
) {

    @GetMapping("/hello")
    @Operation(summary = "Display welcome message", description = "Returns a greeting message based on festive mode")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Welcome message returned successfully")
        ]
    )
    fun sayHello(): String {
        return if (festiveMode) {
            "🎉 Eidkom Mubarak from $companyName!"
        } else {
            "Welcome to Online Ordering by $companyName"
        }
    }
}