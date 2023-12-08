package fr.unilasalle.tdandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.gms.analytics.ecommerce.Product
import fr.unilasalle.tdandroid.ui.theme.MyApplicationTheme
import layout.ProductAdapter

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,  // Use AppDatabase instead of appDatabase.AppDatabase
            "my_database"
        ).build()

        setContentView(R.layout.mainview)
        val recyclerView: RecyclerView = findViewById(R.id.my_products)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val productList = listOf(
            ProductEntity(
                id = 1,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = RatingEntity(id=1, rate = 3.9, count = 120)
            ),
            ProductEntity(
                id = 2,
                title = "Mens Casual Premium Slim Fit T-Shirts",
                price = 22.3,
                description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
                rating = RatingEntity(id=1, rate = 4.1, count = 259)
            ),
            ProductEntity(
                id= 3,
                title= "Mens Cotton Jacket",
                price= 55.99,
                description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
                category= "men's clothing",
                image= "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
                rating =  RatingEntity(id=1, rate= 4.7, count= 500)
            )
        )

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter


    }
}
