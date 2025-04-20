package com.coded.spring.ordering.profiles

import com.coded.spring.ordering.DTO.ProfileRequest
import com.coded.spring.ordering.exceptions.InvalidProfileException
import com.coded.spring.ordering.users.UsersRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProfileService(
    private val profileRepository: ProfileRepository,
    private val usersRepository: UsersRepository
) {

    fun isValidName(name: String): Boolean {
        return name.matches(Regex("^[A-Za-z]+$"))
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^[0-9]{8}$"))
    }

    @Transactional
     fun saveProfile(username: String, request: ProfileRequest): ProfileEntity {
        val user = usersRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        val existingProfile = profileRepository.findByUserId(user.id!!)
        if (existingProfile != null) {
            throw InvalidProfileException("Profile already exists for this user")
        }

        if (!isValidName(request.firstName)) {
            throw InvalidProfileException("First name must contain only letters")
        }

        if (!isValidName(request.lastName)) {
            throw InvalidProfileException("Last name must contain only letters")
        }

        if (!isValidPhone(request.phoneNumber)) {
            throw InvalidProfileException("Phone number must be exactly 8 digits")
        }

        val profile = ProfileEntity(
            firstName = request.firstName,
            lastName = request.lastName,
            phoneNumber = request.phoneNumber,
            userId = user.id!!
        )

        return profileRepository.save(profile)
    }

    fun getByUsername(username: String): ProfileEntity {
        val user = usersRepository.findByUsername(username)
        if (user == null) {
            throw UsernameNotFoundException("User not found")
        }

        val profile = profileRepository.findByUserId(user.id!!)
        if (profile == null) {
            throw InvalidProfileException("Profile not found for this user")
        }

        return profile
    }
}