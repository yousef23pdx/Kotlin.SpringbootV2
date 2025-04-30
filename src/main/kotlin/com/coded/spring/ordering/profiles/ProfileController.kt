package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.DTO.ProfileRequest
import com.coded.spring.ordering.exceptions.InvalidProfileException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/profile")
@Tag(name = "PROFILE", description = "Endpoints for managing user profiles")
class ProfileController(
    private val profileService: ProfileService
) {

    @PostMapping
    @Operation(summary = "Submit user profile", description = "Creates a new profile for the logged-in user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Profile created successfully"),
            ApiResponse(responseCode = "400", description = "Invalid profile data or already exists"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun submitProfile(
        @RequestBody request: ProfileRequest,
        principal: Principal
    ): ResponseEntity<Any> {
        return try {
            profileService.saveProfile(principal.name, request)
            ResponseEntity.ok(mapOf("message" to "Profile created successfully."))
        } catch (e: InvalidProfileException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(mapOf("error" to "Something went wrong."))
        }
    }

    @GetMapping
    @Operation(summary = "Get user profile", description = "Fetches the profile for the logged-in user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Profile fetched successfully"),
            ApiResponse(responseCode = "400", description = "Profile not found or invalid request"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getProfile(principal: Principal): ResponseEntity<Any> {
        return try {
            val profile = profileService.getByUsername(principal.name)
            ResponseEntity.ok(profile)
        } catch (e: InvalidProfileException) {
            ResponseEntity.badRequest().body(mapOf("error" to e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(mapOf("error" to "Something went wrong."))
        }
    }
}