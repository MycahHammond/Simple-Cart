package com.slowcurry.simpleapps.cart.simplecart

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.slowcurry.simpleapps.cart.simplecart.PlaceholderContent.PlaceholderItem
import com.slowcurry.simpleapps.cart.databinding.FragmentCartListItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CartsRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<CartsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCartListItemBinding.inflate(
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

    inner class ViewHolder(binding: FragmentCartListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.cartTitle
        val contentView: TextView = binding.cartTotal

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}