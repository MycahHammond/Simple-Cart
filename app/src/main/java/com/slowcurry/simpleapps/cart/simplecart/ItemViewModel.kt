package com.slowcurry.simpleapps.cart.simplecart

import android.app.Application
import androidx.lifecycle.*
import com.slowcurry.simpleapps.cart.database.CartDatabase
import com.slowcurry.simpleapps.cart.database.CartItem
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ItemViewModel(private val repository: ItemRepository): ViewModel() {

    val allItems: LiveData<List<CartItem>> = repository.allItems.asLiveData()

    fun insert(item: CartItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun deleteTable() = viewModelScope.launch {
        repository.deleteTable()
    }

    fun update(item: CartItem) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: CartItem) = viewModelScope.launch {
        repository.delete(item)
    }


}

class ItemViewModelFactor(private val repository: ItemRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEWMODEL CLASS")
    }

}