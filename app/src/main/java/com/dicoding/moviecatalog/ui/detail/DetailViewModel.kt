package com.dicoding.moviecatalog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.data.genre
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite : LiveData<Boolean> get() = _isFavorite

    fun getGenre(value:List<Int>):List<String?>{
        val list = mutableListOf<String?>()
        value.forEach {
            list.add(genre[it])
        }
        return list
    }

    suspend fun addFavorite(favoriteMovie: FavoriteMovie){
        favoriteRepository.insertMovie(favoriteMovie)
        viewModelScope.launch(Dispatchers.Main) {
            _isFavorite.value = true
        }
    }

    suspend fun deleteFavorite(movieId : Int){
        favoriteRepository.deleteMovie(movieId)
        viewModelScope.launch(Dispatchers.Main){
            _isFavorite.value = false
        }
    }

    suspend fun selectFavorite(movieId:Int){
        _isFavorite.value = favoriteRepository.selectMovie(movieId).isNotEmpty()
    }

    fun discover(result: Result):FavoriteMovie{
        return FavoriteMovie(
            id = result.id,
            adult = result.adult,
            backdrop_path = result.backdrop_path,
            genre_ids = getGenre(result.genre_ids).joinToString(),
            original_language = result.original_language,
            original_title = result.original_title,
            overview = result.overview,
            popularity = result.popularity,
            poster_path = result.poster_path,
            release_date = result.release_date,
            title = result.title,
            video = result.video,
            vote_average = result.vote_average,
            vote_count = result.vote_count,
            name = result.name,
            first_air_date = result.first_air_date
        )
    }


    suspend fun addFavoriteTvShow(favoriteMovie: FavoriteTvShow){
        favoriteRepository.insertTvShow(favoriteMovie)
        viewModelScope.launch(Dispatchers.Main) {
            _isFavorite.value = true
        }
    }

    suspend fun deleteFavoriteTvShow(movieId : Int){
        favoriteRepository.deleteTvShow(movieId)
        viewModelScope.launch(Dispatchers.Main){
            _isFavorite.value = false
        }
    }

    suspend fun selectFavoriteTvShow(movieId:Int){
        _isFavorite.value = favoriteRepository.selectTvShow(movieId).isNotEmpty()
    }

    fun discoverTvShow(result: Result):FavoriteTvShow{
        return FavoriteTvShow(
            id = result.id,
            adult = result.adult,
            backdrop_path = result.backdrop_path,
            genre_ids = getGenre(result.genre_ids).joinToString(),
            original_language = result.original_language,
            original_title = result.original_title,
            overview = result.overview,
            popularity = result.popularity,
            poster_path = result.poster_path,
            release_date = result.release_date,
            title = result.title,
            video = result.video,
            vote_average = result.vote_average,
            vote_count = result.vote_count,
            name = result.name,
            first_air_date = result.first_air_date
        )
    }
}