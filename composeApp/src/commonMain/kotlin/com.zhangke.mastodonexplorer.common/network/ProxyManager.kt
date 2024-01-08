package com.zhangke.mastodonexplorer.common.network

expect class ProxyManager() {

    fun getHttpProxyHost(): String?

    fun getHttpProxyPort(): String?

    fun getHttpsProxyHost(): String?

    fun getHttpsProxyPort(): String?
}