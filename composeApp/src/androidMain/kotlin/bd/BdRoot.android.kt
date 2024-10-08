package bd

import android.content.Context
import androidx.room.RoomDatabaseConstructor
import dataBase.getPeopleDatabase

actual object MyDatabaseCtor : RoomDatabaseConstructor<MyDatabase> {
    val context: Context? = null
    override fun initialize(): MyDatabase {
        return getPeopleDatabase(context!!)
    }
}