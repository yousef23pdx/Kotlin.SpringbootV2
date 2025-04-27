package com.coded.spring.ordering.users

import com.coded.spring.ordering.DTO.UserRequest
import com.coded.spring.ordering.exceptions.TransferFundsException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    private val usersService: UsersService
){

    @PostMapping("/v1/register")
    fun registerUser(@RequestBody request: UserRequest): ResponseEntity<Any> {
        return try {
            val newUser = usersService.registerUser(request)
            ResponseEntity.ok(newUser)
        } catch (e: TransferFundsException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))        }
    }


    @GetMapping("/v1/list")
    fun users() = usersService.listUsers()

}