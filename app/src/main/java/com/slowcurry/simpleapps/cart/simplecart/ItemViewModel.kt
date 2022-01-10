package com.slowcurry.simpleapps.cart.simplecart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.slowcurry.simpleapps.cart.database.CartDatabase

class ItemViewModel(val database: CartDatabase, application: Application): AndroidViewModel(application) {
}