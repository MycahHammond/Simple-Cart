package com.slowcurry.simpleapps.cart

import android.app.Application
import com.slowcurry.simpleapps.cart.database.CartDatabase
import com.slowcurry.simpleapps.cart.simplecart.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SimpleCartApplication: Application(){
    val appScope = CoroutineScope(SupervisorJob())

    val database by lazy{ CartDatabase.getInstance(this, appScope)}
    val repository by lazy { ItemRepository(database.itemDao) }
}