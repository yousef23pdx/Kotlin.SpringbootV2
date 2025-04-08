package com.coded.spring.ordering

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/")

class HelloWorldController(
) {

    @GetMapping("home")
    fun homepage() = "This is the home page for SpeedDash"

    @GetMapping("category")
    fun category() = "This is the category page for SpeedDash"

    @GetMapping("tracker")
    fun tracker() = "This is the tracker page for SpeedDash"

    @GetMapping("profile")
    fun profile() = "This is the profile page for SpeedDash"

    @GetMapping("settings")
    fun settings() = "This is the settings page for SpeedDash"
}
