package com.coded.spring.ordering.orders

import org.springframework.web.bind.annotation.*


@RestController

class OrderController(
    val ordersRepository: OrdersRepository,
    private val ordersService: OrdersService

){
    @GetMapping("/listOrders")
    fun listOrders(): List<OrderEntity> = ordersRepository.findAll()

    @PostMapping("/listOrders")
    fun createOrder(@RequestBody request: CreateOrderRequest) {
        ordersService.createOrder(request.userId)
    }


}

data class CreateOrderRequest(
    val userId: Long
)
