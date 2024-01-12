package com.zhangke.mastodonexplorer.common.db

import app.cash.sqldelight.db.SqlDriver
import com.zhangke.mastodonexplorer.Database

expect class DriverFactory() {

    fun createDriver(dbName: String): SqlDriver?
}

private const val DB_NAME = "mastodon_explorer.db"
fun createDatabase(driverFactory: DriverFactory): Database? {
    val driver = driverFactory.createDriver(DB_NAME) ?: return null
    val database = Database(driver)
    return database
}
