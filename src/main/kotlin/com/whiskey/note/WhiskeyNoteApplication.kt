package com.whiskey.note

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
class WhiskeyNoteApplication

fun main(args: Array<String>) {
    runApplication<WhiskeyNoteApplication>(*args)
}
