package ordering.orders

import items.Item

data class SubmitOrderRequest(
    val itemIds: List<Long>
)

data class Order(
    val id: Long?,
    val userId: Long?,
    val items: List<Item>
)

