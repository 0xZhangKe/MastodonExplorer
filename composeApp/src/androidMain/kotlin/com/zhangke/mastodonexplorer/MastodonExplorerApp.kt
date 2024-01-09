package com.zhangke.mastodonexplorer

import android.app.Application
import com.zhangke.mastodonexplorer.common.utils.initApplication

class MastodonExplorerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initApplication(this)
    }
}