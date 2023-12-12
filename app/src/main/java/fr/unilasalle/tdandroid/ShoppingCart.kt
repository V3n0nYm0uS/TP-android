package fr.unilasalle.tdandroid

data class CartItem(val productName: String, val quantity: Int, val totalPrice: Double)

object ShoppingCart {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(productName: String, quantity: Int, totalPrice: Double) {
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
