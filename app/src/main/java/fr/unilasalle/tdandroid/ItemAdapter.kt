package fr.unilasalle.tdandroid

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.unilasalle.tdandroid.ProductEntity
import fr.unilasalle.tdandroid.R
import java.io.ByteArrayOutputStream

class ItemAdapter(
    private var productList: List<Product>,
    private var context: Context,
    private val onItemClick: (ProductEntity) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val viewTitle: TextView = itemView.findViewById(R.id.item_title)
        val viewPrice: TextView = itemView.findViewById(R.id.item_price)
        val viewImage: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(product: Product){
            //
        }
    }

    fun updateData(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }

    private fun loadImageAsByteArray(imageUrl: String): ByteArray? {
        return try {
            // Load the image using Glide and convert it to ByteArray
            val bitmap = Glide.with(context)
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

    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

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

        holder.itemView.setOnClickListener {
            loadImageAsByteArray(item.image)?.let {
                val productEntity = ProductEntity(
                    id = item.id,
                    title = item.title,
                    price = item.price,
                    description = item.description,
                    category = item.category,
                    image = it,  // Store the image as ByteArray
                    rating = item.rating
                )
                onItemClick.invoke(productEntity)
            }

            // Invoke onItemClick with the converted ProductEntity
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }


}