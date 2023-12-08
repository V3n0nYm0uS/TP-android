package fr.unilasalle.tdandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.unilasalle.tdandroid.ProductEntity
import fr.unilasalle.tdandroid.R

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int){
        val item = productList[position]
        holder.viewId.text = "${item.id}"
        holder.viewTitle.text = "${item.title}"
        holder.viewPrice.text = "${item.price}"
        holder.viewDescription.text = "${item.description}"
        holder.viewCategory.text = "${item.category}"
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val viewId: TextView = itemView.findViewById(R.id.id)
        val viewTitle: TextView = itemView.findViewById(R.id.title)
        val viewPrice: TextView = itemView.findViewById(R.id.price)
        val viewDescription: TextView = itemView.findViewById(R.id.description)
        val viewCategory: TextView = itemView.findViewById(R.id.category)
        // TODO --Image for --val viewImage: TextView = itemView.findViewById(R.id.image)
        // TODO -- recycler for --val viewRating:

        fun bind(product: Product){
            //
        }
    }
}