package com.coded.spring.ordering.orders

import com.coded.spring.ordering.items.Item
import com.coded.spring.ordering.items.ItemEntity
import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named


@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val usersRepository: UsersRepository

) {
    fun listOrders(): List<Order> = ordersRepository.findAll().map { t ->
        Order(
            id = t.id,
            user_id = t.user?.id.toString(),
            items = t.items?.map {
                Item(
                    id = it.id,
                    order_id = it.order_id,
                    quantity = it.quantity,
                    note = it.note,
                    price = it.price,
                    items = it.items

                )
            }
        )
    }


    fun createOrder(userId: Long) {
        val user = usersRepository.findById(userId).orElseThrow {
            IllegalArgumentException("User with id $userId not found")
        }
        val newOrder = OrderEntity(user = user)
        ordersRepository.save(newOrder)
    }

}

data class Order(
    val id: Long?,
    val user_id: String,
    var items: List<Item>?
)
