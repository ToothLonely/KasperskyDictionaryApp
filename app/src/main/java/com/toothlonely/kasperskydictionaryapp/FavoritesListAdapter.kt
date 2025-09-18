package com.toothlonely.kasperskydictionaryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.toothlonely.kasperskydictionaryapp.databinding.ItemWordBinding
import androidx.core.view.isVisible
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY

class FavoritesListAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val word = dataSet[position]

        with(holder) {
            tvWord.text = word

            cvWord.setOnClickListener {

                if (tvDeleteWord.isVisible) {
                    tvDeleteWord.visibility = View.GONE
                } else {
                    tvDeleteWord.visibility = View.VISIBLE
                }
            }

            tvDeleteWord.setOnClickListener {

            }
        }
    }

    override fun getItemCount() = dataSet.size
}
