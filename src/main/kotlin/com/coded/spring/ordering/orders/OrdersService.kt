package com.coded.spring.ordering.orders

import com.coded.spring.ordering.DTO.Order
import com.coded.spring.ordering.items.ItemsRepository
import com.coded.spring.ordering.users.UsersRepository
import jakarta.inject.Named
import com.coded.spring.ordering.DTO.Item


@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val usersRepository: UsersRepository,
    private val itemsRepository: ItemsRepository
) {

    fun submitOrder(username: String, itemIds: List<Long>) {
        val user = usersRepository.findByUsername(username)
            ?: throw IllegalArgumentException("User not found")

        val order = OrderEntity(user = user)
        val savedOrder = ordersRepository.save(order)

        val items = itemsRepository.findAllById(itemIds).map {
            it.order = savedOrder
            it
        }

        itemsRepository.saveAll(items)
    }

    fun listOrdersForUser(username: String): List<Order> {
        val user = usersRepository.findByUsername(username)
            ?: throw IllegalArgumentException("User not found")

        return ordersRepository.findByUserId(user.id!!).map { orderEntity ->
            Order(
                id = orderEntity.id,
                user_id = user.id,
                items = orderEntity.items?.map {
                    Item(
                        id = it.id,
                        order_id = it.order?.id,
                        name = it.name,
                        quantity = it.quantity,
                        note = it.note,
                        price = it.price
                    )
                } ?: listOf()
            )
        }
    }
}