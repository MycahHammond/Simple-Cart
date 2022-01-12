package com.slowcurry.simpleapps.cart.simplecart

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
import androidx.lifecycle.LiveData
import com.slowcurry.simpleapps.cart.R
import com.slowcurry.simpleapps.cart.database.Cart
import com.slowcurry.simpleapps.cart.database.CartItem
import com.slowcurry.simpleapps.cart.databinding.FragmentCartItemBinding


class ItemsRecyclerViewAdapter(
    private val values: LiveData<List<CartItem>>
) : RecyclerView.Adapter<ItemsRecyclerViewAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return this.ItemViewHolder(
            FragmentCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = values.value?.get(position)

        holder.descriptionView.text = item?.description
        holder.costView.text = "$${item?.cost.toString()}"
        holder.itemConstrView.setOnClickListener {
            val imm = getSystemService(it.context, InputMethodManager::class.java)

            //get current text
            holder.editDescription.setText(item?.description)
            holder.editCost.setText(item?.cost.toString())

            //swap visibilities
            holder.descriptionView.visibility = View.GONE
            holder.editDescription.visibility = View.VISIBLE

            holder.costView.visibility = View.GONE
            holder.editCost.visibility = View.VISIBLE

            holder.editDescription.requestFocus()
            holder.editDescription.selectAll()
            imm?.showSoftInput(holder.editDescription, 0)

            //DONE clicked
            holder.editCost.setOnEditorActionListener { textView, i, keyEvent ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    updateItem(
                        holder.editDescription.text.toString(),
                        holder.editCost.text.toString(),
                        item,
                        textView.context
                    )
                    this.notifyItemChanged(position)
                    holder.editCost.visibility = View.GONE
                    holder.costView.visibility = View.VISIBLE

                    holder.editDescription.visibility = View.GONE
                    holder.descriptionView.visibility = View.VISIBLE

                    imm?.hideSoftInputFromWindow(it.windowToken, 0)

                    holder.itemConstrView.clearFocus()
                    return@setOnEditorActionListener true
                }
                false
            }

        }

//        holder.descriptionView.setOnLongClickListener{
//            values.removeAt(position)
//            this.notifyItemChanged(position)
//            it.isLongClickable
//        }


    }

    private fun updateItem(description: String, cost: String, item: CartItem?, context: Context) {
        if(item != null) {
            if (description.isNotEmpty()) item.description = description else item.description =
                context.getString(R.string.item_hint)
            if (cost.isNotEmpty()) item.cost = cost.toDouble() else item.cost = 0.00
        }
    }


    override fun getItemCount(): Int = values.value?.size ?: 0

    inner class ItemViewHolder(binding: FragmentCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val descriptionView: TextView = binding.itemDescription
        val costView: TextView = binding.itemCost
        val editDescription: EditText = binding.itemDescriptionEdit
        val editCost: EditText = binding.itemCostEdit
        val itemConstrView = binding.itemConstraint

        override fun toString(): String {
            return super.toString() + " '" + costView.text + "'"
        }
    }

}

private fun TextView.setOnLongClickListener(function: (View) -> Unit) {

}
