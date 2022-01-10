package com.slowcurry.simpleapps.cart.simplecart

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.slowcurry.simpleapps.cart.database.Cart
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.databinding.FragmentCartItemBinding


class ItemsRecyclerViewAdapter(
    private val values: MutableList<CartItem>
) : RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder(
            FragmentCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.descriptionView.text = item.description
        holder.costView.text = "$${item.cost}"



    }



    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val descriptionView: TextView = binding.itemDescription
        val costView: TextView = binding.itemCost
        val editDescription: EditText = binding.itemDescriptionEdit
        val editCost: EditText = binding.itemCostEdit

        override fun toString(): String {
            return super.toString() + " '" + costView.text + "'"
        }
    }

}