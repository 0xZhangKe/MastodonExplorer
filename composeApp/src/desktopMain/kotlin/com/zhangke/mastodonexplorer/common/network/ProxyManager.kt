package com.zhangke.mastodonexplorer.common.network

actual class ProxyManager {

    actual fun getHttpProxyHost(): String? {
        return System.getProperty("http.proxyHost")
    }

    actual fun getHttpProxyPort(): String? {
        return System.getProperty("http.proxyPort")
    }

    actual fun getHttpsProxyHost(): String? {
        return System.getProperty("https.proxyHost")
    }

    actual fun getHttpsProxyPort(): String? {
        return System.getProperty("https.proxyPort")
    }
}
