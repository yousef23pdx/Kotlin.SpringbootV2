package com.coded.spring.ordering

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/")

class HelloWorldController(
    val ordersRepository: OrdersRepository
){

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

    @PostMapping("orders")
    fun submitOrder(@RequestBody request: OrderRequest): Order{

        val order = Order(null, request.user, request.resturant, request.items.toMutableList())
        return ordersRepository.save(order)
    }
    @GetMapping("allorders")
    fun listOrders(): List<Order> = ordersRepository.findAll()
}

data class OrderRequest(
    val user: String,
    val resturant: String,
    val items: List<String>
)