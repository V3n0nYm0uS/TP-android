package fr.unilasalle.tdandroid

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<RatingEntity>)

    @Query("SELECT * FROM rates")
    suspend fun getProducts(): List<RatingEntity>

    @Query("SELECT * FROM rates WHERE id = :ratingId")
    suspend fun getRatingById(ratingId: Int): RatingEntity?
}
