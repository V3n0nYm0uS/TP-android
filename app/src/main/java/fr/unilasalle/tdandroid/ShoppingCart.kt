package fr.unilasalle.tdandroid
import android.widget.TextView

data class CartItem(val productName: TextView, val quantity: Int, val totalPrice: Double)

object ShoppingCart {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(productName: TextView, quantity: Int, totalPrice: Double) {
        val newItem = CartItem(productName, quantity, totalPrice)
        cartItems.add(newItem)
    }

    fun getCartItems(): List<CartItem> {
        return cartItems.toList()
    }

    fun clearCart() {
        cartItems.clear()
    }
}
