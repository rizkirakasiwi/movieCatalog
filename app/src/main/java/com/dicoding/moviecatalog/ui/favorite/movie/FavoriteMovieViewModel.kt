package com.dicoding.moviecatalog.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
): ViewModel() {

    val favorite = liveData {
        emitSource(favoriteRepository.selectMovie())
    }

}