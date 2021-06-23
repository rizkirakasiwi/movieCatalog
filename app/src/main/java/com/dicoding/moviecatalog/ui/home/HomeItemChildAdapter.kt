package com.dicoding.moviecatalog.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.databinding.RvMovieLayoutBinding
import com.dicoding.moviecatalog.util.load
import kotlinx.coroutines.DelicateCoroutinesApi

class HomeItemChildAdapter :ListAdapter<Result, HomeItemChildViewHolder>(HomeItemChildDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RvMovieLayoutBinding.inflate(inflater, parent, false)
        return HomeItemChildViewHolder(view)
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: HomeItemChildViewHolder, position: Int) {
        holder.bind(getItem(position).poster_path)
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("data" to getItem(position))
            holder.itemView.findNavController().navigate(R.id.action_appbar_home_to_detailFragment, bundle)
        }
    }
}

class HomeItemChildViewHolder(private val view: RvMovieLayoutBinding):RecyclerView.ViewHolder(view.root){
    @DelicateCoroutinesApi
    fun bind(path:String){
        view.posterImg.load(path)
        Log.i("HomeItemChildAdapter", "You see me $path")
    }
}
class HomeItemChildDiffUtil:DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}