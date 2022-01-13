package com.slowcurry.simpleapps.cart.simplecart

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.database.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    val allItems: Flow<List<CartItem>> = itemDao.getCartItems()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: CartItem){
        itemDao.insert(item)
    }

}