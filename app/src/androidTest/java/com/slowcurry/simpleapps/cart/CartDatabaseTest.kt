package com.slowcurry.simpleapps.cart

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.slowcurry.simpleapps.cart.database.Cart
import com.slowcurry.simpleapps.cart.database.CartDao
import com.slowcurry.simpleapps.cart.database.CartDatabase
import com.slowcurry.simpleapps.cart.database.ItemDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
class CartDatabaseTest {

    private lateinit var db: CartDatabase
    private lateinit var cartDao: CartDao
    private lateinit var itemDao: ItemDao

    @Before
    fun createDB(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, CartDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        cartDao = db.cartDao
        itemDao = db.itemDao
    }


    @After
    @Throws(IOException::class)
    fun closeDB(){
        if (this::db.isInitialized) db.close()
    }

    @Test
    @Throws(Exception::class)
    fun  insertAndGetCart() = runBlocking{
        val cart = Cart()
        cart.title = "Home Depot"
        cartDao.insert(cart)

        val cTest: Cart = cartDao.getCart(cart.title)
        assertEquals(cTest.title, cart.title)

    }

}