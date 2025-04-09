package com.coded.spring.ordering.orders

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrdersRepository: JpaRepository<OrderEntity, Long>

@Entity
@Table(name = "orders")
 class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    var restaurant: String,
    //@ElementCollection
    //var items: MutableList<String?> = mutableListOf()
    var items: String


){
    constructor(): this(null, "","", "")
}