package com.dicoding.moviecatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {
    private val _popularMovie = MutableLiveData<Discover?>()
    val popularMovie : LiveData<Discover?> get() = _popularMovie

    private val _trendingMovie = MutableLiveData<Discover?>()
    val trendingMovie : LiveData<Discover?> get() = _trendingMovie

    private val _discover = MutableLiveData<List<Discover?>>()
    val discover : LiveData<List<Discover?>> get() = _discover

    suspend fun trendingMovie(){
        _trendingMovie.value = repository.trendingMovie().data
    }

    suspend fun addDiscover(){
        val listDiscover = mutableListOf<Discover?>()

        val popularMovie = repository.popularMovie().data
        val topRateTv = repository.topRateTvShow().data
        val latestMovie = repository.latestMovie().data
        val latestTv = repository.latestTvShow().data

        if (listDiscover.count() < 3) {
            listDiscover.add(popularMovie)
            listDiscover.add(topRateTv)
            listDiscover.add(latestMovie)
            listDiscover.add(latestTv)
        }

        _discover.value = listDiscover
    }

}