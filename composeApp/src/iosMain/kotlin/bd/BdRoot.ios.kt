package bd

import androidx.room.RoomDatabaseConstructor
import dataBase.getPeopleDatabase

actual object MyDatabaseCtor : RoomDatabaseConstructor<MyDatabase>{
    override fun initialize(): MyDatabase {
         return getPeopleDatabase()
    }

}