package com.slowcurry.simpleapps.cart.database

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Cart::class, CartItem::class], version = 1)
abstract class CartDatabase : RoomDatabase() {

    abstract val cartDao: CartDao
    abstract val itemDao: ItemDao

    companion object {

        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getInstance(context: Context, appScope: CoroutineScope): CartDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDatabase::class.java,
                        "cart_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(ItemDatabaseCallback(appScope))
                        .build()
                    INSTANCE = instance
                }

                return instance

            }
        }
        private class ItemDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                        scope.launch(Dispatchers.IO){
                            populateDatabase(it.itemDao)
                        }
                    }
                }
        }

        suspend fun populateDatabase(itemDao: ItemDao) {
            val items = itemDao.getCount()

            if (items == 0){
                val item = CartItem()
                item.description = "Add Something"
                item.cost = 0.0
                itemDao.insert(item)
            }

        }

    }
}

