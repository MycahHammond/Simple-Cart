package com.slowcurry.simpleapps.cart.simplecart

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.databinding.FragmentCartListBinding

/**
 * A fragment representing a list of Items.
 */
class cartListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCartListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart_list,
            container,
            false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                binding.cartsRecycler.layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                binding.cartsRecycler.adapter = CartsRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            cartListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}