package com.zhangke.mastodonexplorer.common.network

import com.zhangke.mastodonexplorer.common.network.ktorClient
import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.ktorfit

fun newKtorfit(baseUrl: String) = ktorfit {
    baseUrl(baseUrl)
    httpClient(ktorClient)
    converterFactories(
        CallConverterFactory()
    )
}
