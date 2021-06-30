package com.dicoding.moviecatalog.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val discoverRepository : DiscoverRepository
): ViewModel() {
    private val _popularMovie = MutableLiveData<Discover?>()
    val popularMovie : LiveData<Discover?> get() = _popularMovie

    private val _trendingTvShow = MutableLiveData<Discover?>()
    val trendingTvShow : LiveData<Discover?> get() = _trendingTvShow

    private val _discover = MutableLiveData<List<Discover?>>()
    val discover : LiveData<List<Discover?>> get() = _discover

    suspend fun trendingTvShow(){
        _trendingTvShow.value = discoverRepository.tvPopular().data
    }

    suspend fun addDiscover(){
        val listDiscover = mutableListOf<Discover?>()

        val topRateTv = discoverRepository.topRateTvShow().data
        val latestTv = discoverRepository.latestTvShow().data

        if (listDiscover.count() < 1) {
            listDiscover.add(topRateTv)
            listDiscover.add(latestTv)
        }

        _discover.value = listDiscover
    }
}