package com.dicoding.moviecatalog.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.moviecatalog.ui.favorite.movie.FavoriteMovieFragment
import com.dicoding.moviecatalog.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoriteViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragment = listOf(FavoriteMovieFragment(), FavoriteTvShowFragment())

    override fun getItemCount(): Int = fragment.count()

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}