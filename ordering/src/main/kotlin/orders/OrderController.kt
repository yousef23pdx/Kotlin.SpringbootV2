package orders

import ordering.orders.Order
import ordering.orders.SubmitOrderRequest
import ordering.orders.OrdersRepository
import ordering.orders.OrdersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/orders/v1")
@Tag(name = "ORDERING", description = "Endpoints related to order submission and retrieval")
class OrderController(
    val ordersRepository: OrdersRepository,
    private val ordersService: OrdersService
) {

    @PostMapping
    @Operation(summary = "Submit a new order", description = "Submits a new order for the logged-in user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order submitted successfully"),
            ApiResponse(responseCode = "400", description = "Invalid request format"),
            ApiResponse(responseCode = "403", description = "Unauthorized access")
        ]
    )
    fun submitOrder(
        @RequestBody request: SubmitOrderRequest,
        servletRequest: HttpServletRequest
    ): ResponseEntity<String> {
        val userId = servletRequest.getAttribute("userId") as Long
        ordersService.submitOrder(userId, request.itemIds)
        return ResponseEntity.ok("Order submitted successfully.")
    }

    @GetMapping
    @Operation(summary = "List user's orders", description = "Lists all orders submitted by the logged-in user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            ApiResponse(responseCode = "403", description = "Unauthorized access")
        ]
    )
    fun listMyOrders(servletRequest: HttpServletRequest): List<Order> {
        val userId = servletRequest.getAttribute("userId") as Long
        return ordersService.listOrdersForUser(userId)
    }
}