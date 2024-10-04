package bd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import bd.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface BonusDao {
    @Upsert
    suspend fun upsert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Insert
    suspend fun insertBonuses(person: List<Person>)

    @Query("SELECT * FROM person")
    fun getAllBonuses(): Flow<List<Person>>
}