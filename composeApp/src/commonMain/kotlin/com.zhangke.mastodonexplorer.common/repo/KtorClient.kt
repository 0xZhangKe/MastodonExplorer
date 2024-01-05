package com.zhangke.mastodonexplorer.common.repo

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val ktorClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json { isLenient = true; ignoreUnknownKeys = true })
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println("HTTP Client -> $message")
            }
        }
        level = LogLevel.ALL
    }
}