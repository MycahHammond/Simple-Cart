package com.slowcurry.simpleapps.cart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Query("SELECT * FROM items_table ORDER BY position")
     fun getCartItems(): Flow<List<CartItem>>

     @Query("SELECT COUNT(*) FROM items_table")
     fun getCount(): Int


}