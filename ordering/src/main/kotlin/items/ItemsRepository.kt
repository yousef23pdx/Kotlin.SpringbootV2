package items

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemsRepository : JpaRepository<ItemEntity, Long>{
    fun findByOrderId(orderId: Long): List<ItemEntity>
}

@Entity
@Table(name = "items")
data class ItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "items")
    var name: String? = null,

    var quantity: Long? = null,

    var note: String? = null,

    var price: Double? = null,

    @Column(name = "order_id")
    var orderId: Long? = null

) {
    constructor() : this(null, "", 1, "", 0.0, null)
}