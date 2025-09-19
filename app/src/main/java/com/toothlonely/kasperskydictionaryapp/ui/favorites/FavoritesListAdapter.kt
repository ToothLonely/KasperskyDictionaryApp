package com.toothlonely.kasperskydictionaryapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.toothlonely.kasperskydictionaryapp.R
import com.toothlonely.kasperskydictionaryapp.databinding.ItemWordBinding
import com.toothlonely.kasperskydictionaryapp.model.Favorites

class FavoritesListAdapter(private var dataSet: List<Favorites>) :
    RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    private var itemClickListener: OnDeleteClickListener? = null

    interface OnDeleteClickListener {
        fun onClickDelete(id: Int)
    }

    fun setOnClickDeleteListener(listener: OnDeleteClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemViewBinding = ItemWordBinding.bind(itemView)
        val tvWord = itemViewBinding.tvWord
        val tvDeleteWord = itemViewBinding.tvDeleteWord
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val word = dataSet[position].word
        val id = dataSet[position].id

        with(holder) {
            tvWord.text = word

            tvDeleteWord.visibility = View.GONE
            tvWord.setOnClickListener {
                tvDeleteWord.visibility = if (tvDeleteWord.isVisible) View.GONE else View.VISIBLE
            }

            tvDeleteWord.setOnClickListener {
                itemClickListener?.onClickDelete(id)
            }
        }
    }

    override fun getItemCount() = dataSet.size

    fun setNewSet(newSet: List<Favorites>) {
        dataSet = newSet
        notifyDataSetChanged()
    }
}