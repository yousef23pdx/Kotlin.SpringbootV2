package com.coded.spring.ordering.items
import com.coded.spring.ordering.DTO.Item
import com.coded.spring.ordering.DTO.SubmitItemRequest
import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value

@Named
class ItemsService(
    private val itemsRepository: ItemsRepository,
    @Value("\${festive-mode:false}")
    val festiveMode: Boolean

) {
    fun listItems(): List<Item> = itemsRepository.findAll().map { entity ->
        Item(
            id = entity.id,
            order_id = entity.order?.id,
            name = entity.name,
            quantity = entity.quantity,
            note = entity.note,
            price = if (festiveMode) entity.price?.times(0.8) else entity.price
        )
    }

    fun submitItem(request: SubmitItemRequest): ItemEntity {
        val item = ItemEntity(
            name = request.name,
            quantity = request.quantity,
            note = request.note,
            price = request.price
        )
        return itemsRepository.save(item)
    }

}
