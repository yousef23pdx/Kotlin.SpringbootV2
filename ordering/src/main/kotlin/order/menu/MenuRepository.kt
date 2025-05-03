package order.menu

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuEntity, Long>
@Entity
@Table(name = "order/menu")
data class MenuEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String,
    val price: Double
) {
    constructor(): this(null, "", "", 0.0)
}