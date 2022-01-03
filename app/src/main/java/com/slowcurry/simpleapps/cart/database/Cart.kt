package com.slowcurry.simpleapps.cart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.slowcurry.simpleapps.cart.simplecart.ToolBox

@Entity(tableName = "carts_table")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    var  cartID: Long = 0L,

    @ColumnInfo(name = "cart_title")
    var title: String = ToolBox.titlePicker(),

    @ColumnInfo(name = "cart_total")
    var total: Double = 0.00,

    @ColumnInfo(name = "list_id")
    var listID: Long = cartID
)
