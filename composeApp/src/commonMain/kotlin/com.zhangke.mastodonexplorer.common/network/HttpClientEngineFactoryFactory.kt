package com.zhangke.mastodonexplorer.common.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpClientEngineFactoryFactory() {

    fun getHttpClientEngineFactory(): HttpClientEngineFactory<HttpClientEngineConfig>
}
