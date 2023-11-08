package com.whiskey.note.security

import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfiguration(
    private val authenticationManager: CustomAuthenticationManager,
    private val securityContextRepository: SecurityContextRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

            .authorizeExchange {
                it.pathMatchers("/signin", "/signup", "/login").permitAll()
                    .pathMatchers("/members/**").permitAll()
                    .anyExchange()
                    .authenticated()
            }
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .addFilterAt(AuthenticationFilter(jwtTokenProvider), SecurityWebFiltersOrder.HTTP_BASIC)


        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers("/login")
        }
    }

}