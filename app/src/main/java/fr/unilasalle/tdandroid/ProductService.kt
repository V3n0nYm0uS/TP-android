package fr.unilasalle.tdandroid

import androidx.room.Embedded
import androidx.room.PrimaryKey
import retrofit2.http.GET
interface ProductService {

    @GET("products")
   suspend fun getProducts(): List<Product>
}

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingEntity
)

data class Rating(
    val rate: Double,
    val count: Int
)