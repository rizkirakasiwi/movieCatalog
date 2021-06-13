package com.dicoding.moviecatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _discover = MutableLiveData<Discover?>()
    val discover : LiveData<Discover?> get() = _discover

    private val _popularMovie = MutableLiveData<Discover?>()
    val popularMovie : LiveData<Discover?> get() = _popularMovie

    fun getDiscover(){
        viewModelScope.launch(Dispatchers.Main){
            _discover.value = repository.discover().data
        }
    }

    fun getPopularMovie(){
        viewModelScope.launch(Dispatchers.Main) {
            _popularMovie.value = repository.popularMovie().data
        }
    }
}