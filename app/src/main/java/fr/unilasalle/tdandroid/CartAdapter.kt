package fr.unilasalle.tdandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.product_title_textview)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity_textnumber)
        val totalPriceTextView: TextView = itemView.findViewById(R.id.total_price_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        /*holder.productNameTextView.text = currentItem.productName
        holder.quantityTextView.text = "Quantity: ${currentItem.quantity}"
        holder.totalPriceTextView.text = "Total Price: ${currentItem.totalPrice}"*/
        //holder.productNameTextView.text = currentItem.productName
        //holder.quantityTextView.text = "Quantity: ${currentItem.quantity}"
        //holder.totalPriceTextView.text = "Total Price: ${currentItem.totalPrice}"
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}
