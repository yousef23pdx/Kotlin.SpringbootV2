package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.DTO.ProfileRequest
import com.coded.spring.ordering.exceptions.InvalidProfileException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/profile")
class ProfileController(
    private val profileService: ProfileService
) {

    @PostMapping
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