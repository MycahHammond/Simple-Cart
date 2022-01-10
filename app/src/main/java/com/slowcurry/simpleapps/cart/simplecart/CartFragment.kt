package com.slowcurry.simpleapps.cart.simplecart

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.slowcurry.simpleapps.cart.BuildConfig.DEBUG
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.database.CartDatabase
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.database.ItemDao
import com.slowcurry.simpleapps.cart.databinding.FragmentCartBinding

/**
 * A fragment representing a list of Items.
 */
class CartFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCartBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart,
            container,
            false
        )

        //Database-viewModel set up and ref
        val application = requireNotNull(this.activity).application

        val dataSource = CartDatabase.getInstance(application).itemDao


        val items = mutableListOf<CartItem>()

        val i1 = CartItem()
        i1.description = "Nothing Here"
        i1.cost = 0.00

        items.add(i1)

        binding.itemRecycler.layoutManager =when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }

        binding.itemRecycler.adapter = ItemsRecyclerViewAdapter(items)



        return binding.root
    }




//    companion object {
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "column-count"
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            CartFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }
}