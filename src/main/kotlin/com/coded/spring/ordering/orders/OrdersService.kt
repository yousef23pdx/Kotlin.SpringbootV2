package com.coded.spring.ordering.orders
import jakarta.inject.Named

@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
) {

    fun listOrders(): List<OrderEntity> = ordersRepository.findAll().map {
        OrderEntity(
            username = it.username,
            restaurant = it.restaurant,
            items = it.items

        )
    }
}