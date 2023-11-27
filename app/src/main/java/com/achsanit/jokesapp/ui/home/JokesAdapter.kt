package com.achsanit.jokesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.achsanit.jokesapp.R
import com.achsanit.jokesapp.data.response.ResultItem
import com.achsanit.jokesapp.databinding.ItemJokesBinding

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemJokesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResultItem) {
            binding.tvJokes.text =
                this.itemView.context.getString(R.string.quote_placeholder, data.value)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ResultItem>() {
        override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(data: List<ResultItem?>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemJokesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}