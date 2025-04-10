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
    var id: Long?,
    var user_id: Long?,

){
    constructor(): this(null, null)
}