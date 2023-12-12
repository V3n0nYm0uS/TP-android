package fr.unilasalle.tdandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Panier : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_panier)

            val cartRecyclerView: RecyclerView = findViewById(R.id.cart_recyclerview)


            val cartItems = ShoppingCart.getCartItems()


            //val cartAdapter = CartAdapter(cartItems)
            //cartRecyclerView.adapter = cartAdapter
            //cartRecyclerView.layoutManager = LinearLayoutManager(this)


            //displayCartContents()
        }

    }
