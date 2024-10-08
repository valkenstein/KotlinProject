package bd

import androidx.room.ConstructedBy
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.RoomDatabaseConstructor
import bd.dao.BonusDao
import bd.model.Person

@Database(
    entities = [Person::class],
    version = 1
)
@ConstructedBy(MyDatabaseCtor::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun peopleDao(): BonusDao

}

expect object MyDatabaseCtor : RoomDatabaseConstructor<MyDatabase>