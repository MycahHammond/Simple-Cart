package com.slowcurry.simpleapps.cart.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {

    @Insert
    suspend fun insert(cart: Cart)

    @Query("SELECT * FROM carts_table WHERE cart_title = :title")
    suspend fun getCart(title: String): Cart
}