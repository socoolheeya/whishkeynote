package com.whiskey.note.main.member.router

import com.whiskey.note.main.member.handler.MemberHandler
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


@Configuration
@RequiredArgsConstructor
class MemberRouter(
    private val memberHandler: MemberHandler
) {
    @Bean
    fun memberRouters() {
        router {
            path("/members").nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("", memberHandler::getMembers)
                    GET("/{id}", memberHandler::getMember)
                    POST("", memberHandler::createMember)
                    PUT("/{id}", memberHandler::updateMember)
                    DELETE("/{id}", memberHandler::deleteMember)
                }
            }
        }
    }
}
