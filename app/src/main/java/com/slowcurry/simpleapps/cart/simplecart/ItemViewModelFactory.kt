package com.slowcurry.simpleapps.cart.simplecart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.slowcurry.simpleapps.cart.database.CartDatabase
import java.lang.IllegalArgumentException

class ItemViewModelFactory(
    private val dataSource: CartDatabase,
    private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartDatabase::class.java)){
            return ItemViewModelFactory(dataSource, application) as T
        }
        else{
            throw IllegalArgumentException("Unknown ViewModel Class.")
        }
    }

}