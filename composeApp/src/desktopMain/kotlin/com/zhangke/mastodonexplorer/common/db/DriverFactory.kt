package com.zhangke.mastodonexplorer.common.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.zhangke.mastodonexplorer.Database
import java.io.File

actual class DriverFactory {

    actual fun createDriver(dbName: String): SqlDriver? {
        val userDir = System.getProperty("user.home")
        val dbDirPath = "$userDir/sqlite/mastodon_explorer"
        val dbDir = File(dbDirPath)
        if (!dbDir.exists()) {
            dbDir.mkdirs()
        }
        val dbPath = "jdbc:sqlite:$dbDirPath/$dbName"
        println("db path: $dbPath")
        val driver: SqlDriver = JdbcSqliteDriver(dbPath)
        Database.Schema.create(driver)
        return driver
    }
}
