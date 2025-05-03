package items

data class Item(
    val id: Long?,
    val orderId: Long?,
    val name: String?,
    val quantity: Long?,
    val note: String?,
    val price: Double?
)

data class SubmitItemRequest(
    val name: String,
    val quantity: Long,
    val note: String?,
    val price: Double
)