package com.slowcurry.simpleapps.cart.simplecart

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.slowcurry.simpleapps.cart.databinding.FragmentCartItemBinding

import com.slowcurry.simpleapps.cart.simplecart.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemsRecyclerViewAdapter(
    private val values: MutableList<PlaceholderContent.PlaceholderItem>
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
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemDescription
        val contentView: TextView = binding.itemCost

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}