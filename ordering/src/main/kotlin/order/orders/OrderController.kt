package order.orders

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order/orders/v1")
@Tag(name = "ORDERING", description = "Endpoints related to order submission and retrieval")
class OrderController(
    private val ordersRepository: OrdersRepository,
    private val ordersService: OrdersService
) {

    @PostMapping("/create")
    @Operation(summary = "Create a new order", description = "Creates a new order along with items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Order created successfully"),
            ApiResponse(responseCode = "400", description = "Invalid input"),
            ApiResponse(responseCode = "403", description = "Unauthorized access")
        ]
    )
    fun createOrder(
        @RequestBody request: CreateOrderRequest,
        servletRequest: HttpServletRequest
    ): ResponseEntity<Order> {
        println("🔥 [OrderController] createOrder() hit")
        val userId = servletRequest.getAttribute("userId") as Long
        val order = ordersService.createOrder(userId, request.items)
        return ResponseEntity.ok(order)
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