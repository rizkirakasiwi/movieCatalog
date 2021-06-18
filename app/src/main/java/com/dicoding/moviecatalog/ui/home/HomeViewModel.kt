package com.dicoding.moviecatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.Repository
import com.xwray.groupie.Group
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _popularMovie = MutableLiveData<Discover?>()
    val popularMovie : LiveData<Discover?> get() = _popularMovie

    private val _trendingMovie = MutableLiveData<Discover?>()
    val trendingMovie : LiveData<Discover?> get() = _trendingMovie

    private val _topRateTvShow = MutableLiveData<Discover?>()
    val topRateTvShow : LiveData<Discover?> get() = _topRateTvShow


    private val grouAdapter = mutableListOf<Group>()

    private val _adapterGroup = MutableLiveData<MutableList<Group>>()
    val adapterGroup : LiveData<MutableList<Group>> get() =  _adapterGroup

    suspend fun getPopularMovie(){
        _popularMovie.value = repository.popularMovie().data
    }

    suspend fun getTrendingMovie(){
        _trendingMovie.value = repository.trendingMovie().data
    }

    suspend fun getTopRateTvShow(){
        _topRateTvShow.value = repository.topRateTvShow().data
    }

    fun addAdapter(item:Group){
        grouAdapter.add(item)
        _adapterGroup.value = grouAdapter
    }
}