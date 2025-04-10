package com.coded.spring.ordering.orders
import jakarta.inject.Named

@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
) {
    fun listOrders(): List<Order> = ordersRepository.findAll().map {
        Order(
            id = it.id,
            user_id = it.user_id

        )

    }
}

data class Order(
    var id: Long?,
    var user_id: Long?
)