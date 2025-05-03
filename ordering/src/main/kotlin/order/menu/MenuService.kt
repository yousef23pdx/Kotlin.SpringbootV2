package order.menu

import jakarta.inject.Named
import order.menu.MenuEntity
import order.menu.MenuRepository

@Named
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun getMenu(): List<MenuEntity> = menuRepository.findAll()
}