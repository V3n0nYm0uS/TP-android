package fr.unilasalle.tdandroid
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Panier : AppCompatActivity() {


        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            val intent = Intent(this, Panier::class.java)

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_panier)
            val cartRecyclerView: RecyclerView = findViewById(R.id.cart_recyclerview)
            val cartItems = ShoppingCart.getCartItems()
            val cartAdapter = CartAdapter(cartItems)
            cartRecyclerView.adapter = cartAdapter
            cartRecyclerView.layoutManager = LinearLayoutManager(this)
           // displayCartContents()

        }

    }
