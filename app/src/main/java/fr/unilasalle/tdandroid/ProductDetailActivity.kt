// ProductDetailActivity.kt
package fr.unilasalle.tdandroid

import ProductDetailViewModel
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import fr.unilasalle.tdandroid.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductDetailViewModel
    private lateinit var productPriceTextView: TextView
    private lateinit var quantityTextNumber: EditText
    private lateinit var totalPriceTextView: TextView
    private lateinit var addToCartButton: Button
    private lateinit var productTitleTextview: TextView
    private lateinit var productDescriptionTextView: TextView
    private lateinit var productRateNumberTextView: TextView
    private lateinit var productRatingBar: RatingBar
    private lateinit var productImageView: ImageView


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        viewModel = ViewModelProvider(this, ProductDetailViewModel.ListViewModelFactory()).get(ProductDetailViewModel::class.java)


        val backButton: Button = findViewById(R.id.back_button)
        productPriceTextView = findViewById(R.id.product_price_textview)
        quantityTextNumber = findViewById(R.id.quantity_textnumber)
        totalPriceTextView = findViewById(R.id.total_price_textview)
        addToCartButton = findViewById(R.id.add_to_cart_button)
        productTitleTextview = findViewById(R.id.product_title_textview)
        productDescriptionTextView = findViewById(R.id.product_description_text)
        productRateNumberTextView = findViewById(R.id.nbRating)
        productRatingBar = findViewById(R.id.ratingBar)
        productImageView = findViewById(R.id.imageView2)

        val selectedProduct = intent.extras?.getParcelable("selectedProduct") as ProductEntity?
        if (selectedProduct != null) {
        // Update UI with selectedProduct data
            productTitleTextview.text = selectedProduct.title
            productPriceTextView.text = selectedProduct.price.toString()
            productDescriptionTextView.text = selectedProduct.description
            productRateNumberTextView.text = selectedProduct.rating?.count.toString()
            if (selectedProduct.rating?.rate != null) {
                productRatingBar.rating = selectedProduct.rating?.rate.toFloat()
            }
            Glide.with(productImageView.context)
                .load(selectedProduct.image)
                .into(productImageView)
                } else {
                    // Handle the case when the product is not available
                }

        backButton.setOnClickListener {
            finish()
        }

        val defaultProductPrice = 10.0
        viewModel.productPrice = defaultProductPrice
        productPriceTextView.text = defaultProductPrice.toString()

        // Set a TextWatcher on quantityTextNumber to update total price dynamically
        quantityTextNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateQuantity(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Observe changes in ViewModel and update UI accordingly
        viewModel.totalPrice.observe(this, Observer {
            totalPriceTextView.text = it.toString()
        })

        addToCartButton.setOnClickListener {
            val selectedProduct = intent.extras?.getParcelable("selectedProduct") as ProductEntity?
            val quantity = quantityTextNumber.text.toString().toDoubleOrNull() ?: 0.0

            if (selectedProduct != null) {
                // Call the addToCart function in the ViewModel
                addToCart(selectedProduct, quantity)

                // Set the result to be sent back to MainActivity
                val intent = Intent()
                intent.putExtra("selectedProduct", selectedProduct)
                intent.putExtra("quantity", quantity)
                setResult(RESULT_OK, intent)

                // Finish the activity
                finish()
            } else {
                // Handle the case when the product is not available
                println("Error: Product not available")
            }
        }
    }

    private fun updateQuantity(quantity: String) {
        viewModel.quantity = if (quantity.isBlank()) 0.0 else quantity.toDouble()
        viewModel.calculateTotalPrice()
    }


    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun loadImageAsByteArray(imageUrl: String): ByteArray? {
        return try {
            // Load the image using Glide and convert it to ByteArray
            val bitmap = Glide.with(this)
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

    fun addToCart(product: ProductEntity, quantity: Double) {
        try {
            // Initialize or obtain the Room database instance
            val db = Room.databaseBuilder(
                // Use your application context here,
                // replace 'applicationContext' with the actual context if needed
                this.application,
                AppDatabase::class.java,
                "app"
            ).build()

            // Check if the product is already in the cart
            runBlocking {
                val productInDatabase = db.productDao().getProductById(product.id)
                if (productInDatabase != null) {
                    // Product exists in the database, proceed to add to the cart
                    val existingCartItem = db.cartDao().getCartByProductId(product.id)
                    if (existingCartItem != null) {
                        // Product already in the cart, update the quantity
                        existingCartItem.quantity += quantity
                        db.cartDao().insertCart(existingCartItem)
                    } else {
                        // Product not in the cart, create a new entry
                        val cartEntity = CartEntity(id=product.id, productId = product.id, quantity = quantity)
                        db.cartDao().insertCart(cartEntity)
                    }
                    // Notify the user that the product has been added to the cart
                    Toast.makeText(application, "Product added to the cart", Toast.LENGTH_SHORT).show()
                } else {
                    // Product does not exist in the ProductEntity database, create it
                    db.productDao().insertProduct(product)

                    // Now, proceed to add the product to the cart
                    val cartEntity = CartEntity(id=product.id, productId = product.id, quantity = quantity)
                    db.cartDao().insertCart(cartEntity)

                    // Notify the user that the product has been added to the cart
                    Toast.makeText(application, "Product added to the cart", Toast.LENGTH_SHORT).show()
                }
            }



            // Notify the user that the product has been added to the cart
            Toast.makeText(application, "Product added to the cart", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            // Handle exceptions (e.g., database errors)
            Log.d("myact", "${e}")
            Toast.makeText(application, "Error adding product to the cart", Toast.LENGTH_SHORT).show()
        }
    }

}