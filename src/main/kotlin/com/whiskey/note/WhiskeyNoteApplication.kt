package com.whiskey.note

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhiskeyNoteApplication

fun main(args: Array<String>) {
    runApplication<WhiskeyNoteApplication>(*args)
}
