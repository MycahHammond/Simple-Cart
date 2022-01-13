package com.slowcurry.simpleapps.cart.simplecart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
    val itemViewModel: ItemViewModel  by viewModels {
        ItemViewModelFactor(application.repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        application = requireActivity().application as SimpleCartApplication


        val binding: FragmentCartBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cart,
            container,
            false
        )

        val adapter = ItemsRecyclerViewAdapter(itemViewModel)
        binding.itemRecycler.adapter = adapter
        binding.itemRecycler.layoutManager = LinearLayoutManager(context)

        itemViewModel.allItems.observe(viewLifecycleOwner){
            it.let { adapter.submitList(it) }

            var total = 0.0
            for (i in it){
                total += i.cost
            }
            binding.totalAmount.text = "$${String.format("%.2f", total)}"
        }

        binding.fab.setOnClickListener{
            val item = CartItem()
            item.description = "Tap to Edit"
            item.cost = 0.00
            itemViewModel.insert(item)
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clearCart){
            itemViewModel.deleteTable()

        }
        return super.onOptionsItemSelected(item)
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