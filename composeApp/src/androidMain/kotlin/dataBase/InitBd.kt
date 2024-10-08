package dataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import bd.BdRoot
import bd.MyDatabase
import kotlinx.coroutines.Dispatchers

fun getPeopleDatabase(context: Context): MyDatabase {
    val dbFile = context.getDatabasePath("root.db")

    return Room.databaseBuilder<MyDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

