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


    fun calculateTotalPrice() {
        val productPrice = productPriceTextView.text.toString().toDoubleOrNull() ?: 0.0
        val quantity = quantityTextNumber.text.toString().toDoubleOrNull() ?: 0.0
        val totalPrice = productPrice * quantity
        totalPriceTextView.text = totalPrice.toString()
    }
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


        val defaultProductPrice = productPriceTextView
        productPriceTextView.text = defaultProductPrice.toString()
        quantityTextNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTotalPrice()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

}
}
