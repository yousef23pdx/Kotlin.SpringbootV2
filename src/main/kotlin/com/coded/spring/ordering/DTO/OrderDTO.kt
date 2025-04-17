package com.coded.spring.ordering.DTO

import com.coded.spring.ordering.items.ItemEntity

data class SubmitOrderRequest(
    val itemIds: List<Long>
)

data class Order(
    val id: Long?,
    val user_id: Long?,
    val items: List<Item>
)

