package com.zhangke.mastodonexplorer.common.db

import app.cash.sqldelight.db.SqlDriver

actual class DriverFactory {

    actual fun createDriver(dbName: String): SqlDriver? {
//        return NativeSqliteDriver(Database.Schema, dbName)
        return null
    }
}
