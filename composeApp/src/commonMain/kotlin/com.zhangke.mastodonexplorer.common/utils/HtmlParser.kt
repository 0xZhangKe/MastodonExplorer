package com.zhangke.mastodonexplorer.common.utils

import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class HtmlParser {

    suspend fun parse(html: String): String = withContext(Dispatchers.IO) {
        val builder = StringBuilder()
        val handler = KsoupHtmlHandler.Builder()
            .onText { builder.append(it) }
            .build()
        val parser = KsoupHtmlParser(handler)
        parser.write(html)
        parser.end()
        return@withContext builder.toString()
    }
}
