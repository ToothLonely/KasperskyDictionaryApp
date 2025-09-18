package com.toothlonely.kasperskydictionaryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toothlonely.kasperskydictionaryapp.databinding.ItemWordBinding
import androidx.core.view.isVisible

class FavoritesListAdapter(private var dataSet: List<String>) :
    RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    private var itemClickListener: OnBtnDeleteClickListener? = null

    interface OnBtnDeleteClickListener {
        fun onClickDelete(word: String)
    }

    fun setOnClickDeleteListener(listener: OnBtnDeleteClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemViewBinding = ItemWordBinding.bind(itemView)
        val tvWord = itemViewBinding.tvWord
        val tvDeleteWord = itemViewBinding.tvDeleteWord
        val cvWord = itemViewBinding.cvWord
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
        val word = dataSet[position]

        with(holder) {
            tvWord.text = word

            tvWord.setOnClickListener {

                if (tvDeleteWord.isVisible) {
                    tvDeleteWord.visibility = View.GONE
                } else {
                    tvDeleteWord.visibility = View.VISIBLE
                }
            }

            tvDeleteWord.setOnClickListener {
                itemClickListener?.onClickDelete(word)
            }
        }
    }

    override fun getItemCount() = dataSet.size
}
