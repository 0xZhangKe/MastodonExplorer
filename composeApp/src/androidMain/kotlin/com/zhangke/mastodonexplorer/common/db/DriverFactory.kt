package com.zhangke.mastodonexplorer.common.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.zhangke.mastodonexplorer.Database
import com.zhangke.mastodonexplorer.common.utils.appContext

actual class DriverFactory {

    actual fun createDriver(dbName: String): SqlDriver? {
        return AndroidSqliteDriver(Database.Schema, appContext, dbName)
    }
}
