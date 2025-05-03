package ordering.orders
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

import jakarta.inject.Named

@Named
interface OrdersRepository: JpaRepository<OrderEntity, Long>{
    fun findByUserId(userId: Long): List<OrderEntity>
}

@Entity
@Table(name = "orders")
class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_id")
    var userId: Long? = null,

) {
    constructor() : this(null, null)
}