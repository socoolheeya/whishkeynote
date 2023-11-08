package com.whiskey.note.security

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Slf4j
class AuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): WebFilter {

    @Override
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token: String = resolveToken(exchange.request).orEmpty();
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            val authentication: Authentication = jwtTokenProvider.getAuthentication(token);
            return chain. filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
        }
        return chain.filter(exchange);
    }


    private fun resolveToken(request: ServerHttpRequest): String? {
        val bearerToken: String = request.headers.getFirst(HttpHeaders.AUTHORIZATION).orEmpty()
        return if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else null;
    }

}