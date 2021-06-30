package com.dicoding.moviecatalog.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val discoverRepository: DiscoverRepository
) : ViewModel() {

    private val _popularMovie = MutableLiveData<Discover?>()
    val popularMovie : LiveData<Discover?> get() = _popularMovie

    private val _trendingMovie = MutableLiveData<Discover?>()
    val trendingMovie : LiveData<Discover?> get() = _trendingMovie

    private val _discover = MutableLiveData<List<Discover?>>()
    val discover : LiveData<List<Discover?>> get() = _discover

    suspend fun trendingMovie(){
        _trendingMovie.value = discoverRepository.trendingMovie().data
    }

    suspend fun addDiscover(){
        val listDiscover = mutableListOf<Discover?>()

        val popularMovie = discoverRepository.popularMovie().data
        val latestMovie = discoverRepository.latestMovie().data

        if (listDiscover.count() < 1) {
            listDiscover.add(popularMovie)
            listDiscover.add(latestMovie)
        }

        _discover.value = listDiscover
    }
}