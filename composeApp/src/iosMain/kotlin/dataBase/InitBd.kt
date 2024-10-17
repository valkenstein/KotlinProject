package dataBase

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

//fun getPeopleDatabase(): BdRoot {
//    val dbFile = NSHomeDirectory() + "/root.db"
//    return Room.databaseBuilder<BdRoot>(name = dbFile,
//        factory = { BdRoot::class.instantiateImpl() }).setDriver(BundledSQLiteDriver()).build()
//}

//fun createRoomDatabase(): AppDatabase {
//    val dbFile = "${fileDirectory()}/$dbFileName"
//    return Room.databaseBuilder<AppDatabase>(name = dbFile)
//        .setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.IO)
//        .build()
//}
@OptIn(ExperimentalForeignApi::class)
private fun fileDirectory(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path!!
}

//actual fun createTestDatabase(): MyDatabase {
//    val dbFilePath = documentDirectory() + "/people.db"
//    return Room.databaseBuilder<MyDatabase>(
//        name = dbFilePath,
//    ).setDriver(BundledSQLiteDriver()).build()
//}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}