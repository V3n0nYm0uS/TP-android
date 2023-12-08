package fr.unilasalle.tdandroid

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(cartEntity: List<CartEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(cartEntity: CartEntity)

    @Query("SELECT * FROM carts")
    suspend fun getProducts(): List<CartEntity>

}
