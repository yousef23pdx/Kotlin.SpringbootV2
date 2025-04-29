package com.coded.spring.ordering.profiles

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ProfileRepository : JpaRepository<ProfileEntity, Long> {
    fun findByUserId(userId: Long): ProfileEntity?
}

@Entity
@Table(name = "profiles")
data class ProfileEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val userId: Long = 0

)