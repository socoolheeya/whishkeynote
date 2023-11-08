package com.whiskey.note.security

import io.jsonwebtoken.Claims
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Component
class CustomAuthenticationManager(
    private val jwtTokenProvider: JwtTokenProvider
): ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken: String = authentication.credentials.toString()
        val username: String = jwtTokenProvider.decodeJwtToken(authToken).body.subject;

        val claims: Claims = jwtTokenProvider.getClaims(authToken)
        val roles: String = claims["roles"] as String

        return Mono.just(jwtTokenProvider.validateToken(authToken))
            .filter {
                valid -> valid
            }
            .switchIfEmpty(Mono.empty())
            .map {
                UsernamePasswordAuthenticationToken(
                    username,
                    "",
                    roles.split(",").stream()
                        .map(::SimpleGrantedAuthority)
                        .collect(Collectors.toList())
                )
            }
    }
}