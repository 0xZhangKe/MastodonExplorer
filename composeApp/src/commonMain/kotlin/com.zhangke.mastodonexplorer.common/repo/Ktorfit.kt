package com.zhangke.mastodonexplorer.common.repo

import de.jensklingenberg.ktorfit.converter.builtin.CallConverterFactory
import de.jensklingenberg.ktorfit.ktorfit

fun newKtorfit(baseUrl: String) = ktorfit {
    baseUrl(baseUrl)
    httpClient(ktorClient)
    converterFactories(
        CallConverterFactory()
    )
}
