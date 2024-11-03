package kv.apps.mywarehouse.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kv.apps.mywarehouse.productsDB.Product
import kv.apps.mywarehouse.productsDB.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val allProducts: LiveData<List<Product>> = repository.allProducts

    fun insert(product: Product) {
        viewModelScope.launch {
            repository.insert(product)
        }
    }

    fun delete(product: Product) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }

    fun update(product: Product) {
        viewModelScope.launch {
            repository.update(product)
        }
    }
}
