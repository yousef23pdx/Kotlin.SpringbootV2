package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Named
interface OrdersRepository: JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
 class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    var restaurant: String,
    @ElementCollection
    var items: MutableList<String?> = mutableListOf()

){
    constructor(): this(null, "","", mutableListOf())
}