package com.whiskey.note.main.token.model

import org.springframework.stereotype.Component

@Component
class JwtProperties {

    companion object {
        const val SECRET_KEY: String = "1b6ca74a3a7afe8b3d127234f5ea374bbc44ba7edf10ca58baf6b6b6f7c24f52"
        const val EXPIRATION_TIME: Int = 72000000 // 2hour
        const val TOKEN_PREFIX = "Bearer "
        const val HEADER_STRING = "Authorization"
    }
}