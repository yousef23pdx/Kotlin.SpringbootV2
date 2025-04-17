package com.coded.spring.ordering.orders

import com.coded.spring.ordering.DTO.Order
import com.coded.spring.ordering.DTO.SubmitOrderRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/orders/v1")
class OrderController(
    val ordersRepository: OrdersRepository,
    private val ordersService: OrdersService

){

    @PostMapping
    fun submitOrder(
        @RequestBody request: SubmitOrderRequest,
        principal: Principal
    ): ResponseEntity<String> {
        val username = principal.name // logged-in user
        ordersService.submitOrder(username, request.itemIds)
        return ResponseEntity.ok("Order submitted successfully.")
    }

    @GetMapping
    fun listMyOrders(principal: Principal): List<Order> {
        return ordersService.listOrdersForUser(principal.name)
    }


}

