package fr.unilasalle.tdandroid

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartEntity: List<CartEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartEntity: CartEntity)

    @Query("SELECT * FROM carts")
    suspend fun getCarts(): List<CartEntity>

    @Query("SELECT * FROM carts WHERE productId = :productId")
    suspend fun getCartByProductId(productId: Int): CartEntity?


}
