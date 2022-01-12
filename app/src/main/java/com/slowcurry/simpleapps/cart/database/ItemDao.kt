package com.slowcurry.simpleapps.cart.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Query("SELECT * FROM items_table ORDER BY position")
     fun getCartItems(): LiveData<List<CartItem>>


}