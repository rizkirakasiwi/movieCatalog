package com.dicoding.moviecatalog.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.databinding.HomeBannerLayoutBinding
import com.dicoding.moviecatalog.util.load
import kotlinx.coroutines.DelicateCoroutinesApi

class BannerAdapter:ListAdapter<Result, BannerViewHolder>(BannerDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HomeBannerLayoutBinding.inflate(inflater, parent, false)
        return BannerViewHolder(view)
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("data" to getItem(position))
            holder.itemView.findNavController().navigate(R.id.action_appbar_home_to_detailFragment, bundle)
        }
    }

}
class BannerViewHolder(private val view:HomeBannerLayoutBinding):RecyclerView.ViewHolder(view.root){

    @DelicateCoroutinesApi
    fun bind(data:Result){
        view.backdropImage.load(data.backdrop_path)
        view.titleText.text = data.title ?: data.name
        val rating : Float = data.vote_average.toFloat() / 2f
        view.ratingImage.rating = rating
        view.textRating.text = data.vote_average.toString()
    }
}
class BannerDiffUtil:DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}