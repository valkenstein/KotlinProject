package dataBase

import androidx.room.*
import dataBase.dao.FantasyPremierLeagueDao
import dataBase.model.GameFixture
import dataBase.model.Player
import dataBase.model.Team
import kotlinx.datetime.LocalDateTime

//internal expect object AppDatabaseCtor : RoomDatabaseConstructor<AppDatabase>
//
//@Database(entities = [Team::class, Player::class, GameFixture::class], version = 1)
//@ConstructedBy(AppDatabaseCtor::class)
//@TypeConverters(LocalDateTimeConverter::class)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun fantasyPremierLeagueDao(): FantasyPremierLeagueDao
//}
//
//internal const val dbFileName = "fantasypremierleague.db"
//
//
//class LocalDateTimeConverter {
//    @TypeConverter
//    fun fromTimestamp(value: String?): LocalDateTime? {
//        return value?.let { LocalDateTime.parse(it) }
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: LocalDateTime?): String? {
//        return date?.toString()
//    }
//}
