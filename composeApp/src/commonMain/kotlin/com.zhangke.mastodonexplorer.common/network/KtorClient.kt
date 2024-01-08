package com.zhangke.mastodonexplorer.common.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val ktorClient = HttpClient(HttpClientEngineFactoryFactory().getHttpClientEngineFactory()) {
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
    engine {
        val proxyManager = ProxyManager()
        val httpHost = proxyManager.getHttpProxyHost()
        val httpPort = proxyManager.getHttpProxyPort()
        val httpsHost = proxyManager.getHttpsProxyHost()
        val httpsPort = proxyManager.getHttpsProxyPort()
        println("http proxy: $httpHost:$httpPort")
        println("https proxy: $httpsHost:$httpsPort")
        if (!httpHost.isNullOrEmpty() && !httpPort.isNullOrEmpty()) {
            proxy = ProxyBuilder.http(Url("http://$httpHost:$httpPort/"))
        }
        if (!httpsHost.isNullOrEmpty() && !httpsPort.isNullOrEmpty()) {
            proxy = ProxyBuilder.http(Url("https://$httpsHost:$httpsPort/"))
        }
    }
}
