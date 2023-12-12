package fr.unilasalle.tdandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CartAdapter(private val items: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        // Set values for each item in the ViewHolder
        holder.itemNameTextView.text = item.productName
        holder.itemPriceTextView.text = "Price: ${item.totalPrice}"
        holder.itemQuantityTextView.text = "Quantity: ${item.quantity}"


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageView: ImageView = itemView.findViewById(R.id.item_image)
        val itemNameTextView: TextView = itemView.findViewById(R.id.product_price_textview)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.item_price)
        val itemQuantityTextView: TextView = itemView.findViewById(R.id.quantity_textnumber)
    }
}

