package fr.unilasalle.tdandroid

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    private fun initDb() {
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app"
        ).build()
    }

    suspend fun loadImageAsByteArray(imageUrl: String): ByteArray? {
        return try {
            // Load the image using Glide and convert it to ByteArray
            val bitmap = Glide.with(this@MainActivity)
                .asBitmap()
                .load(imageUrl)
                .submit()
                .get()

            // Convert Bitmap to ByteArray
            convertBitmapToByteArray(bitmap)
        } catch (e: Exception) {
            null
        }
    }

    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.initDb()

        val productDao = db.productDao()

        val itemView: RecyclerView = findViewById(R.id.home_items)
        val categoriesView: Spinner = findViewById(R.id.home_categories)

        /*
        // Insert
        runBlocking {
            val deferred = async(Dispatchers.IO) {
                productDao.insertProducts(productList)
            }
            deferred.await()
        }
        */

        /*
        runBlocking {
            val deferred = async(Dispatchers.IO) {
                val newProductList = productDao.getProducts()
                val adapter = ProductAdapter(newProductList)
                recyclerView.adapter = adapter
            }
            deferred.await()
        }
         */


        runBlocking {
            val deferred = async(Dispatchers.IO) {
                // Get products
                val products = RetrofitInstance.productService.getProducts()
                // Modify the image field of each Product in the list
                val productEntities = products.map { product ->
                    loadImageAsByteArray(product.image)?.let {
                        ProductEntity(
                            id = product.id,
                            title = product.title,
                            price = product.price,
                            description = product.description,
                            category = product.category,
                            image = it,
                            rating = product.rating
                        )
                    }
                }
                // Extract categories
                products?.let{listProduct ->
                    val categories: List<String> = listProduct.map{it.category}.distinct()
                    val adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item,
                        categories
                    )
                    categoriesView.adapter = adapter
                }

                // Add product to their recycler via ItemAdapter
                val adapter = ItemAdapter(products)
                itemView.adapter = adapter
            }
            deferred.await()
        }


    }

}
