package com.slowcurry.simpleapps.cart.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items_table")
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    var itemID: Long = 0L,

    @ColumnInfo(name = "list_id")
    var listID: Long = 0L,

    @ColumnInfo(name = "position")
    var position: Int = 0,

    @ColumnInfo(name = "item_description")
    var description: String = "",

    @ColumnInfo(name = "item_cost")
    var cost: Double = 0.00



)