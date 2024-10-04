package bd.bd

import androidx.room.RoomDatabase
import androidx.room.Database
import bd.dao.BonusDao
import bd.model.Person

@Database(
entities = [Person::class],
version = 1
)
abstract class BdRoot: RoomDatabase() {

    abstract fun peopleDao(): BonusDao

}