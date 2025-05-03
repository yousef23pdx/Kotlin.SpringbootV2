package items

import jakarta.servlet.http.HttpServletRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import ordering.client.AuthenticationClient
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "ORDERING", description = "Endpoints related to managing menu items")
class ItemsController(
    private val itemsService: ItemsService,
    private val authenticationClient: AuthenticationClient
) {

    @GetMapping("/listItems")
    @Operation(summary = "List all menu items", description = "Returns a list of all available items.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Items listed successfully"),
            ApiResponse(responseCode = "500", description = "Server error while retrieving items")
        ]
    )
    fun listItems(): List<Item> = itemsService.listItems()

    @PostMapping("/submitItems")
    @Operation(summary = "Submit a new menu item", description = "Allows authenticated users to add a new item to the menu.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Item submitted successfully"),
            ApiResponse(responseCode = "400", description = "Invalid item data"),
            ApiResponse(responseCode = "401", description = "Unauthorized request")
        ]
    )
    fun submitItem(
        @RequestBody request: SubmitItemRequest,
        servletRequest: HttpServletRequest
    ): ItemEntity {
        val authHeader = servletRequest.getHeader("Authorization")
            ?: throw IllegalStateException("Missing Authorization header")

        authenticationClient.checkToken(authHeader.removePrefix("Bearer ").trim())

        return itemsService.submitItem(request)
    }
}