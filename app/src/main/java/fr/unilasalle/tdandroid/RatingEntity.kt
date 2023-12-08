package fr.unilasalle.tdandroid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class RatingEntity(
    @PrimaryKey val id: Int,
    val rate: Double,
    val count: Int
)
