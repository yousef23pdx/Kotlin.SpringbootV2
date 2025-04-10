package com.coded.spring.ordering.orders

import org.springframework.web.bind.annotation.*


@RestController

class OrderController(
    val ordersRepository: OrdersRepository
){
    @GetMapping("/listOrders")
    fun listOrders(): List<OrderEntity> = ordersRepository.findAll()
}
