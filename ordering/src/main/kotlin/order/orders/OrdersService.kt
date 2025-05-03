package order.orders

import jakarta.inject.Named

@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val itemsRepository: ItemsRepository
) {

    fun createOrder(userId: Long, items: List<Item>): Order {
        val newOrder = ordersRepository.save(OrderEntity(userId = userId))

        val itemEntities = items.map {
            ItemEntity(
                name = it.name,
                quantity = it.quantity,
                note = it.note,
                price = it.price,
                orderId = newOrder.id!!
            )
        }

        itemsRepository.saveAll(itemEntities)

        return Order(
            id = newOrder.id!!,
            userId = userId,
            items = itemEntities.map {
                Item(
                    id = it.id,
                    orderId = it.orderId,
                    name = it.name,
                    quantity = it.quantity,
                    note = it.note,
                    price = it.price
                )
            }
        )
    }

    fun listOrdersForUser(userId: Long): List<Order> {
        return ordersRepository.findByUserId(userId).map { orderEntity ->
            val itemEntities = itemsRepository.findByOrderId(orderEntity.id!!)

            val items = itemEntities.map {
                Item(
                    id = it.id,
                    orderId = it.orderId,
                    name = it.name,
                    quantity = it.quantity,
                    note = it.note,
                    price = it.price
                )
            }

            Order(
                id = orderEntity.id!!,
                userId = userId,
                items = items
            )
        }
    }
}