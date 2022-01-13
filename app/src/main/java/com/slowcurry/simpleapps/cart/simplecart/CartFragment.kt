package com.slowcurry.simpleapps.cart.simplecart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.SimpleCartApplication
import com.slowcurry.simpleapps.cart.database.CartDatabase
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.databinding.FragmentCartBinding

/**
 * A fragment representing a list of Items.
 */
class CartFragment : Fragment() {

    private lateinit var application : SimpleCartApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        application = requireActivity().application as SimpleCartApplication
        val itemViewModel: ItemViewModel by viewModels {
            ItemViewModelFactor(application.repository)
        }

        val binding: FragmentCartBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart,
            container,
            false
        )

        val adapter = ItemsRecyclerViewAdapter()
        binding.itemRecycler.adapter = adapter
        binding.itemRecycler.layoutManager = LinearLayoutManager(context)

        itemViewModel.allItems.observe(viewLifecycleOwner){
            it.let { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener{
            var item = CartItem()
            item.description = "New thing"
            item.cost = 0.0
            itemViewModel.insert(item)
        }

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