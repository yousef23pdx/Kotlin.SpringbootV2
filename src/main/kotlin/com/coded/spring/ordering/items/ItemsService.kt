package com.coded.spring.ordering.items
import jakarta.inject.Named

@Named
class ItemsService(
    private val itemsRepository: ItemsRepository,
) {
    fun listItems(): List<Item> = itemsRepository.findAll().map { entity ->
        Item(
            id = entity.id,
            order_id = entity.order_id,
            items = entity.items,
            quantity = entity.quantity,
            note = entity.note,
            price = entity.price
        )
    }
}

data class Item(
    val id: Long?,
    val order_id: Long?,
    val items: String?,
    val quantity: Long?,
    val note: String?,
    val price: Double?
)