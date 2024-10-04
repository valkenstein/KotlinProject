package dataBase

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import bd.bd.BdRoot

fun getPeopleDatabase(context: Context): BdRoot {
    val dbFile = context.getDatabasePath("root.db")
    return Room.databaseBuilder<BdRoot>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}