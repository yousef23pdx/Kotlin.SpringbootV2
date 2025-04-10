package com.coded.spring.ordering.orders
import com.coded.spring.ordering.items.ItemEntity
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository


import com.coded.spring.ordering.users.UserEntity
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @OneToMany(mappedBy = "order_id")
    val items: List<ItemEntity>? = null

){
    constructor(): this(null, UserEntity(), listOf())
}