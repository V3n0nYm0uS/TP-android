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
import fr.unilasalle.tdandroid.ui.theme.MyApplicationTheme
import layout.ProductAdapter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.mainview)
        val recyclerView: RecyclerView = findViewById(R.id.my_products)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val productList = listOf(
            Product(
                id = 1,
                title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = 109.95,
                description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                rating = Rating(rate = 3.9, count = 120)
            ),
            Product(
                id = 2,
                title = "Mens Casual Premium Slim Fit T-Shirts",
                price = 22.3,
                description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
                category = "men's clothing",
                image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
                rating = Rating(rate = 4.1, count = 259)
            )
        )

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter


    }
}
