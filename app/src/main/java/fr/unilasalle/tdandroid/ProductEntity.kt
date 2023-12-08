package fr.unilasalle.tdandroid

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
   @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
   @Embedded
   val rating: RatingEntity
)

@Entity(tableName="rates")
data class RatingEntity(
 val rate: Double,
 val count: Int
)