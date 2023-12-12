package fr.unilasalle.tdandroid


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var productPriceTextView: TextView
    private lateinit var quantityTextNumber: EditText
    private lateinit var totalPriceTextView: TextView
    private lateinit var addToCartButton: Button
<<<<<<< HEAD:app/src/main/java/fr/unilasalle/tdandroid/ProductDetailActivity.kt


    fun calculateTotalPrice() {
        val productPrice = productPriceTextView.text.toString().toDoubleOrNull() ?: 0.0
        val quantity = quantityTextNumber.text.toString().toDoubleOrNull() ?: 0.0
        val totalPrice = productPrice * quantity
        totalPriceTextView.text = totalPrice.toString()
    }
=======
    private lateinit var producttitletextview: TextView
>>>>>>> d1bc2d28a6bd696e9c6a0a675579b8e35a6cb3ac:app/src/main/java/fr/unilasalle/tdandroid/product_details.kt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val backButton: Button = findViewById(R.id.back_button)
        productPriceTextView = findViewById(R.id.product_price_textview)
        quantityTextNumber = findViewById(R.id.quantity_textnumber)
        totalPriceTextView = findViewById(R.id.total_price_textview)
        addToCartButton = findViewById(R.id.add_to_cart_button)

        backButton.setOnClickListener(View.OnClickListener {
            finish()
        })
        val defaultProductPrice = 10.0
        productPriceTextView.text = defaultProductPrice.toString()

        // Set a TextWatcher on quantityTextNumber to update total price dynamically
        quantityTextNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTotalPrice()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

<<<<<<< HEAD:app/src/main/java/fr/unilasalle/tdandroid/ProductDetailActivity.kt
}
=======
        addToCartButton.setOnClickListener {
            val totalPrice = totalPriceTextView.text.toString().toDouble()
            val quantity = quantityTextNumber.text.toString().toDoubleOrNull() ?: 0.0
            val productName = producttitletextview

            ShoppingCart.addItem(productName, quantity.toInt(), totalPrice)

            println("Product added to cart: $productName, Quantity: $quantity, Total Price: $totalPrice")

        }
    }

    private fun calculateTotalPrice() {
        val productPrice = productPriceTextView.text.toString().toDoubleOrNull() ?: 0.0
        val quantity = quantityTextNumber.text.toString().toDoubleOrNull() ?: 0.0

        // Calculate the total price by multiplying the product price and quantity
        val totalPrice = productPrice * quantity

        // Update the total_price_textview with the calculated total price
        totalPriceTextView.text = totalPrice.toString()
    }
>>>>>>> d1bc2d28a6bd696e9c6a0a675579b8e35a6cb3ac:app/src/main/java/fr/unilasalle/tdandroid/product_details.kt
}
