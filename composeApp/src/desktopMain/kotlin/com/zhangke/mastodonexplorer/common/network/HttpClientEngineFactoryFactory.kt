package com.zhangke.mastodonexplorer.common.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual class HttpClientEngineFactoryFactory {

    actual fun getHttpClientEngineFactory(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return CIO
    }
}
