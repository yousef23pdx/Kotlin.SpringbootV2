package com.coded.spring.ordering.menu

import jakarta.inject.Named

@Named
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun getMenu(): List<MenuEntity> = menuRepository.findAll()
}