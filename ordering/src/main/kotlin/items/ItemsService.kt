package items

import jakarta.inject.Named
import org.springframework.beans.factory.annotation.Value

@Named
class ItemsService(
    private val itemsRepository: ItemsRepository,
    @Value("\${festive-mode:false}")
    private val festiveMode: Boolean
) {

    fun listItems(): List<Item> = itemsRepository.findAll().map { entity ->
        Item(
            id = entity.id,
            orderId = entity.orderId,
            name = entity.name,
            quantity = entity.quantity,
            note = entity.note,
            price = calculatePrice(entity.price)
        )
    }

    fun submitItem(request: SubmitItemRequest): ItemEntity {
        val item = ItemEntity(
            name = request.name,
            quantity = request.quantity ?: 0,
            note = request.note,
            price = request.price ?: 0.0,
            orderId = 0 // Default to 0, actual value set on order placement
        )
        return itemsRepository.save(item)
    }

    private fun calculatePrice(originalPrice: Double?): Double? {
        return if (festiveMode) originalPrice?.times(0.8) else originalPrice
    }
}