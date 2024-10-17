package dataBase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)