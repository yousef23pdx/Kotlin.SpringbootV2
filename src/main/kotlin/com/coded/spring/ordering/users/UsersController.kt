package com.coded.spring.ordering.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersService: UsersService
){
    //@GetMapping("/users/v1/list")
    @GetMapping("/welcome")
    fun users() = usersService.listUsers()

    //@GetMapping("/menus/v1/menu")

}