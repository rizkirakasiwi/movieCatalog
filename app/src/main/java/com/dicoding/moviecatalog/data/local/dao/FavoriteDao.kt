package com.dicoding.moviecatalog.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(favoriteMovie: FavoriteMovie)

    @Query("DELETE FROM favoritemovie WHERE id = :movieId")
    fun deleteMovie(movieId : Int)

    @Query("SELECT * FROM favoritemovie WHERE id = :movieId")
    fun selectMovie(movieId : Int):List<FavoriteMovie>

    @Query("SELECT * FROM favoritemovie")
    fun selectMovie():DataSource.Factory<Int,FavoriteMovie>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShow(favoriteTvShow: FavoriteTvShow)

    @Query("DELETE FROM favoritetvshow WHERE id = :movieId")
    fun deleteTvshow(movieId : Int)

    @Query("SELECT * FROM favoritetvshow WHERE id = :movieId")
    fun selectTvShow(movieId : Int):List<FavoriteTvShow>

    @Query("SELECT * FROM favoritetvshow")
    fun selectTvShow():DataSource.Factory<Int,FavoriteTvShow>
}