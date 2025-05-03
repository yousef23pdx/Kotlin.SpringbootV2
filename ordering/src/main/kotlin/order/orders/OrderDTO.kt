package order.orders

data class Item(
    val id: Long?,
    val orderId: Long?,
    val name: String?,
    val quantity: Long?,
    val note: String?,
    val price: Double?
)

data class Order(
    val id: Long?,
    val userId: Long?,
    val items: List<Item>
)

data class CreateOrderRequest(
    val items: List<Item>
)
