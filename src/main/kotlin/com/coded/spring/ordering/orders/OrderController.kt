package com.coded.spring.ordering.orders

import com.coded.spring.ordering.DTO.Order
import com.coded.spring.ordering.DTO.SubmitOrderRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
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
        principal: Principal
    ): ResponseEntity<String> {
        val username = principal.name
        ordersService.submitOrder(username, request.itemIds)
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
    fun listMyOrders(principal: Principal): List<Order> {
        return ordersService.listOrdersForUser(principal.name)
    }
}