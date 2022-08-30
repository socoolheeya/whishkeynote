package com.whiskey.note.member.repository

import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class QMemberRepository(
    private val client: DatabaseClient
) {



}