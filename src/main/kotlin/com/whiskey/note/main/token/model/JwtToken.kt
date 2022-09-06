package com.whiskey.note.main.token.model

import java.math.BigInteger

data class JwtToken(
    private val memberId: BigInteger,
    private val accessToken: String,
)