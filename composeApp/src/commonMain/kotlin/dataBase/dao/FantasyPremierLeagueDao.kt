package dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dataBase.model.GameFixture
import dataBase.model.Player
import dataBase.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface FantasyPremierLeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamList(teamList: List<Team>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerList(playerList: List<Player>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixtureList(fixtureList: List<GameFixture>)

    @Query("SELECT * FROM Player")
    fun getPlayerListAsFlow(): Flow<List<Player>>

    @Query("SELECT * FROM Player WHERE id = :id")
    suspend fun getPlayer(id: Int): Player

    @Query("SELECT * FROM GameFixture")
    fun getFixtureListAsFlow(): Flow<List<GameFixture>>

    @Query("SELECT * FROM GameFixture WHERE id = :id")
    suspend fun getFixture(id: Int): GameFixture
}
