package com.whiskey.note.configuration

import io.r2dbc.spi.ConnectionFactory
import org.mariadb.r2dbc.MariadbConnectionConfiguration
import org.mariadb.r2dbc.MariadbConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
//@EnableR2dbcRepositories
class DataBaseConfiguration: AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory
        = MariadbConnectionFactory.from(
            MariadbConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .username("test")
                .password("1234")
                .database("whiskey_note")
                .build()
        )

    override fun databaseClient(): DatabaseClient {
        val factory = connectionFactory();
        return DatabaseClient.create(factory);
    }
}