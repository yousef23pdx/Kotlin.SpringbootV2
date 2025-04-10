package com.coded.spring.ordering.items

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
    var order_id: Long?,
    var items: String?,
    var quantity: Long?,
    var note: String?,
    var price: Double?

) {constructor(): this(null,1,"",1,"",1.0)}