package com.dicoding.moviecatalog.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow

interface FavoriteRepository {
    suspend fun insertMovie(favoriteMovie: FavoriteMovie)
    suspend fun deleteMovie(movieId : Int)
    suspend fun selectMovie(movieId:Int):List<FavoriteMovie>
    suspend fun selectMovie():LiveData<PagedList<FavoriteMovie>>

    suspend fun insertTvShow(favoriteTvShow: FavoriteTvShow)
    suspend fun deleteTvShow(movieId : Int)
    suspend fun selectTvShow(movieId:Int):List<FavoriteTvShow>
    suspend fun selectTvShow():LiveData<PagedList<FavoriteTvShow>>
}