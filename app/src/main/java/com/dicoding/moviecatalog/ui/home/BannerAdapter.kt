package com.dicoding.moviecatalog.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.databinding.HomeBannerLayoutBinding
import kotlinx.coroutines.*

class BannerAdapter:ListAdapter<Result, BannerViewHolder>(BannerDiffUtil()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = HomeBannerLayoutBinding.inflate(inflater, parent, false)
        return BannerViewHolder(view)
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
class BannerViewHolder(private val view:HomeBannerLayoutBinding):RecyclerView.ViewHolder(view.root){

    @DelicateCoroutinesApi
    fun bind(data:Result){
        val imageUrl = BuildConfig.imageUrl
        val path = data.poster_path.removePrefix("/")
        GlobalScope.launch(Dispatchers.Main){
            val load = withContext(Dispatchers.IO){
                Glide.with(itemView).load(imageUrl+path)
            }

            load.into(view.backdropImage)
        }

        view.titleText.text = data.title
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