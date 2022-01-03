package com.slowcurry.simpleapps.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.database.CartDao
import com.slowcurry.simpleapps.cart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


    }
}