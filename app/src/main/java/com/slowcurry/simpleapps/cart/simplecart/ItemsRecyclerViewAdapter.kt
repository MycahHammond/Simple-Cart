package com.slowcurry.simpleapps.cart.simplecart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.*
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.text.set
import androidx.databinding.adapters.ViewBindingAdapter.setOnLongClickListener
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.database.Cart
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.databinding.FragmentCartItemBinding


class ItemsRecyclerViewAdapter(
    private val itemViewModel: ItemViewModel
) : ListAdapter<CartItem, ItemsRecyclerViewAdapter.ItemViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return this.ItemViewHolder(
            FragmentCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)


        holder.descriptionView.text = item?.description
        holder.costView.text = "$${String.format("%.2f", item?.cost)}"
        holder.itemConstrView.setOnClickListener {
            val imm = getSystemService(it.context, InputMethodManager::class.java)
            if (holder.deleteButton.visibility != View.GONE) holder.deleteButton.visibility = View.GONE

            //get current text
            holder.editDescription.setText(item?.description)
            holder.editCost.setText(item?.cost.toString())

            //swap visibilities
            holder.descriptionView.visibility = View.GONE
            holder.editDescription.visibility = View.VISIBLE

            holder.costView.visibility = View.GONE
            holder.editCost.visibility = View.VISIBLE

            holder.editDescription.requestFocus()

            holder.saveButton.visibility = View.VISIBLE
            imm?.showSoftInput(holder.editDescription, 0)

            //Save clicked
            holder.saveButton.setOnClickListener {
                updateItem(
                    holder.editDescription.text.toString(),
                    holder.editCost.text.toString(),
                    item,
                    itemViewModel

                )
                this.notifyItemChanged(position)
                holder.editCost.visibility = View.GONE
                holder.costView.visibility = View.VISIBLE

                holder.editDescription.visibility = View.GONE
                holder.descriptionView.visibility = View.VISIBLE

                holder.saveButton.visibility = View.GONE

                //imm?.hideSoftInputFromWindow(it.windowToken, 0)

                holder.itemConstrView.clearFocus()
            }

        }



        holder.itemConstrView.setOnLongClickListener(){
            holder.deleteButton.visibility = View.VISIBLE
            holder.deleteButton.setOnClickListener{
                itemViewModel.delete(item)
                it.visibility = View.GONE
            }
            it.isLongClickable
        }


}

    private fun updateItem(description: String, cost: String, item: CartItem?, itemViewModel: ItemViewModel) {
        if(item != null) {
            if (description.isNotEmpty()) item.description = description else item.description = "Tap to edit"
            if (cost.isNotEmpty()) item.cost = cost.toDouble() else item.cost = 0.00
            itemViewModel.update(item)
        }
    }



    inner class ItemViewHolder(binding: FragmentCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val descriptionView: TextView = binding.itemDescription
        val costView: TextView = binding.itemCost
        val editDescription: EditText = binding.itemDescriptionEdit
        val editCost: EditText = binding.itemCostEdit
        val itemConstrView = binding.itemConstraint
        val saveButton = binding.saveButton
        val deleteButton = binding.deleteButton

        override fun toString(): String {
            return super.toString() + " '" + costView.text + "'"
        }
    }

    companion object{
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<CartItem>(){
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return  oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }

}

