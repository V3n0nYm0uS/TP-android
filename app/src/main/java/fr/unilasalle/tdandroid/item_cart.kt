package fr.unilasalle.tdandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.unilasalle.tdandroid.ShoppingCart
class item_cart : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_cart)
        data class CartItem(
            val productName: String,
            val quantity: Int,
            val totalPrice: Double
        )

        //data class CartItem(val productName: String, val quantity: Int, val totalPrice: Double)

    }




}