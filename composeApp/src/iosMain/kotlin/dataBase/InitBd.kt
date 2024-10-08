package dataBase

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import bd.BdRoot
import bd.MyDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

//fun getPeopleDatabase(): BdRoot {
//    val dbFile = NSHomeDirectory() + "/root.db"
//    return Room.databaseBuilder<BdRoot>(name = dbFile,
//        factory = { BdRoot::class.instantiateImpl() }).setDriver(BundledSQLiteDriver()).build()
//}

fun getPeopleDatabase(): MyDatabase {
    val dbFilePath = documentDirectory() + "/people.db"
    return Room.databaseBuilder<MyDatabase>(
        name = dbFilePath,
    ).setDriver(BundledSQLiteDriver()).build()
}

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