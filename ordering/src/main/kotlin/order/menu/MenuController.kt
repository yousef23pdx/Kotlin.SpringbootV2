package order.menu

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "HOME_PAGE", description = "Endpoints related to displaying the homepage menu")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping("/order/menu/v1/list")
    @Operation(
        summary = "List all menu items for homepage",
        description = "Returns all menu entries including discounts if active"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Menu listed successfully"),
            ApiResponse(responseCode = "500", description = "Server error while fetching menu")
        ]
    )
    fun getMenu(): List<MenuEntity> = menuService.getMenu()
}