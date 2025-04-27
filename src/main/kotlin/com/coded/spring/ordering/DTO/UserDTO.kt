package com.coded.spring.ordering.DTO

data class UserRequest(
    val name: String,
    val age: Int,
    val username: String,
    val password: String
)

data class UserResponse(
    val id: Long,
    val username: String
)