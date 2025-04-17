package com.coded.spring.ordering.menu

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuController(
    private val menuService: MenuService
) {
    @GetMapping("/menus/v1/menu")
    fun getMenu(): List<MenuEntity> = menuService.getMenu()
}