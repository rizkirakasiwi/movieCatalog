package com.dicoding.moviecatalog.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.databinding.RvHomeLayoutBinding

class HomeItemAdapter:ListAdapter<Discover, HomeItemViewHolder>(HomeItemDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RvHomeLayoutBinding.inflate(inflater, parent, false)
        return HomeItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
class HomeItemViewHolder(private val binding : RvHomeLayoutBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(discover: Discover){
        binding.titleText.text = discover.category
        val adapter = HomeItemChildAdapter()
        adapter.submitList(discover.results)
        binding.rvHomeLitem.adapter = adapter
    }
}
class HomeItemDiffUtil:DiffUtil.ItemCallback<Discover>() {
    override fun areItemsTheSame(oldItem: Discover, newItem: Discover): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(oldItem: Discover, newItem: Discover): Boolean {
        return oldItem == newItem
    }
}