package com.slowcurry.simpleapps.cart.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("DELETE FROM items_table")
    suspend fun deleteTable()

    @Query("SELECT * FROM items_table ORDER BY position")
     fun getCartItems(): Flow<List<CartItem>>

     @Query("SELECT COUNT(*) FROM items_table")
     fun getCount(): Int


}