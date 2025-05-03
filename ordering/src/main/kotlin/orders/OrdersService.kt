package ordering.orders

import items.Item
import items.ItemEntity
import items.ItemsRepository
import jakarta.inject.Named

@Named
class OrdersService(
    private val ordersRepository: OrdersRepository,
    private val itemsRepository: ItemsRepository
) {

    fun submitOrder(userId: Long, itemIds: List<Long>) {
        val order = ordersRepository.save(OrderEntity(userId = userId))

        val updatedItems = itemsRepository.findAllById(itemIds).map { itemEntity ->
            itemEntity.copy(orderId = order.id!!)
        }

        itemsRepository.saveAll(updatedItems)
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