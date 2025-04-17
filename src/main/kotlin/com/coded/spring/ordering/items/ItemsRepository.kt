package com.coded.spring.ordering.items

import com.coded.spring.ordering.orders.OrderEntity
import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemsRepository : JpaRepository<ItemEntity, Long>



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

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: OrderEntity? = null

) {
    constructor(): this(null, "", 1, "", 0.0, null)
}