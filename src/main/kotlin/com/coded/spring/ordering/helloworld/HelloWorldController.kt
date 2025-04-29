package com.coded.spring.ordering.helloworld

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController(
    @Value("\${company.name}") val companyName: String,
    @Value("\${festive-mode:false}") val festiveMode: Boolean
) {

    @GetMapping("/hello")
    fun sayHello(): String {
        return if (festiveMode) {
            "🎉 Eidkom Mubarak from $companyName!"
        } else {
            "Welcome to Online Ordering by $companyName"
        }
    }
}