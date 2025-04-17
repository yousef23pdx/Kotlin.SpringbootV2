package com.coded.spring.ordering.authentication

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity

class SecurityConfig(

    private val userDetailsService: UserDetailsService

){

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder ()

//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
//        http.csrf { it.disable() }
//
//        http.authorizeHttpRequests {
//            it.requestMatchers("/welcome").permitAll()
//                .anyRequest().authenticated()
//        }.formLogin { it.defaultSuccessUrl("/welcome",true)}
//            .userDetailsService(userDetailsService)
//
//        return http.build()
//            }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    // Public endpoint
                    .requestMatchers("/menus/**").permitAll()

                    // Protected endpoints
                    .requestMatchers("/orders/**").authenticated()

                    // All others
                    .anyRequest().authenticated()
            }
            .formLogin { it.defaultSuccessUrl("/menus/v1/menu", true) }
            .userDetailsService(userDetailsService)

        return http.build()
    }


    }





