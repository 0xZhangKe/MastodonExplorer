package com.zhangke.mastodonexplorer.common.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.zhangke.mastodonexplorer.Database

actual class DriverFactory {

    actual fun createDriver(dbName: String): SqlDriver {
        return NativeSqliteDriver(Database.Schema, dbName)
    }
}
