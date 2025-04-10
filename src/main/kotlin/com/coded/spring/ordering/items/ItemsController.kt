package com.coded.spring.ordering.items

import org.springframework.web.bind.annotation.*

@RestController


class ItemsController(

    private val itemsService: ItemsService

) {
    @GetMapping("/listItems")
    fun listItems(): List<Item> = itemsService.listItems()

}