// ProductDetailViewModel.kt
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import fr.unilasalle.tdandroid.AppDatabase
import fr.unilasalle.tdandroid.ProductEntity
import java.lang.IllegalArgumentException
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ProductDetailViewModel() : ViewModel() {



    private val _totalPrice = MutableLiveData<Double>()


    val totalPrice: LiveData<Double> get() = _totalPrice
    var productPrice: Double = 0.0
    var quantity: Double = 0.0
    var totalPriceValue: Double = 0.0
        set(value) {
            field = value
            _totalPrice.value = value
        }

    private val _item = MutableLiveData<ProductEntity>()
    val item: LiveData<ProductEntity> = _item

    fun calculateTotalPrice() {
        totalPriceValue = productPrice * quantity
    }



    class ListViewModelFactory() : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
                ProductDetailViewModel() as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}
