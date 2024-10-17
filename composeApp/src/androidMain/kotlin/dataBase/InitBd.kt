package dataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers


//fun createRoomDatabase(ctx: Context): AppDatabase {
//    val dbFile = ctx.getDatabasePath(dbFileName)
//    return Room.databaseBuilder<AppDatabase>(ctx, dbFile.absolutePath)
//        .setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.IO)
//        .build()
//}
//actual fun createTestDatabase(): MyDatabase {
//    val applicationContext: Context? = null
//    val dbFile = applicationContext!!.getDatabasePath("root.db")
//
//    return Room.databaseBuilder<MyDatabase>(
//        context = applicationContext.applicationContext,
//        name = dbFile.absolutePath
//    )
//        .setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.IO)
//        .build()
//}

