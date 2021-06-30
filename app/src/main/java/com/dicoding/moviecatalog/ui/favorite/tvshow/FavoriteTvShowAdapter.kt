package com.dicoding.moviecatalog.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow
import com.dicoding.moviecatalog.databinding.FavoriteLayoutBinding
import com.dicoding.moviecatalog.util.load
import com.dicoding.moviecatalog.util.toResult
import kotlinx.coroutines.DelicateCoroutinesApi

class FavoriteTvShowAdapter : PagedListAdapter<FavoriteTvShow, FavoriteTvShowAdapter.FavoriteViewHolder>(
    FavoriteDiffUtil()
){
    inner class FavoriteViewHolder(private val binding : FavoriteLayoutBinding):RecyclerView.ViewHolder(binding.root){
        @DelicateCoroutinesApi
        fun bind(favoriteMovie: FavoriteTvShow){
            binding.genreText.text = favoriteMovie.genre_ids
            binding.posterImg.load(favoriteMovie.backdrop_path)
            binding.titleText.text = favoriteMovie.name
            binding.ratingBar.rating = favoriteMovie.vote_average.toFloat() / 2f
        }
    }

    class FavoriteDiffUtil : DiffUtil.ItemCallback<FavoriteTvShow>(){
        override fun areItemsTheSame(oldItem: FavoriteTvShow, newItem: FavoriteTvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteTvShow, newItem: FavoriteTvShow): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FavoriteLayoutBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(view)
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position)!!)


        holder.itemView.setOnClickListener {
            val bundle = bundleOf("data" to getItem(position)?.toResult())
            holder.itemView.findNavController()
                .navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }

}
