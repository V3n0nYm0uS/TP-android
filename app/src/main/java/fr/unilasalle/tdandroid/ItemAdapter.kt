package fr.unilasalle.tdandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.unilasalle.tdandroid.ProductEntity
import fr.unilasalle.tdandroid.R

class ItemAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int){
        val item = productList[position]
        Glide.with(holder.viewImage.context)
            .load(item.image)
            .into(holder.viewImage)
        holder.viewTitle.text = item.title
        holder.viewPrice.text = "${item.price}"

        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val viewTitle: TextView = itemView.findViewById(R.id.item_title)
        val viewPrice: TextView = itemView.findViewById(R.id.item_price)
        val viewImage: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(product: Product){
            //
        }
    }
}