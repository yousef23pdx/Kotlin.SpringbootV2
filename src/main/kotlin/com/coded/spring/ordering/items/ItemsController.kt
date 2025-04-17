package com.coded.spring.ordering.items

import com.coded.spring.ordering.DTO.Item
import com.coded.spring.ordering.DTO.SubmitItemRequest
import org.springframework.web.bind.annotation.*

@RestController


class ItemsController(

    private val itemsService: ItemsService

) {
    @GetMapping("/listItems")
    fun listItems(): List<Item> = itemsService.listItems()

    @PostMapping("/submitItems")
    fun submitItem(@RequestBody request: SubmitItemRequest): ItemEntity {
        return itemsService.submitItem(request)
    }

}