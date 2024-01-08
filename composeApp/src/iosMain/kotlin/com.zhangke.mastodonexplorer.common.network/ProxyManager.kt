package com.zhangke.mastodonexplorer.common.network

actual class ProxyManager {

    actual fun getHttpProxyHost(): String? {
        return null
    }

    actual fun getHttpProxyPort(): String? {
        return null
    }

    actual fun getHttpsProxyHost(): String? {
        return null
    }

    actual fun getHttpsProxyPort(): String? {
        return null
    }
}
