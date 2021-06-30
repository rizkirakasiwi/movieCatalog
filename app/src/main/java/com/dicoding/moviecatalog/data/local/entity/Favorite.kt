package com.dicoding.moviecatalog.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dicoding.moviecatalog.data.Result

@Entity
data class FavoriteMovie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,
    val adult: Boolean,
    val backdrop_path: String? = null,
    var genre_ids: String,
    val original_language: String,
    val original_title: String? = null,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val name : String? = null,
    val first_air_date: String? = null
)

@Entity
data class FavoriteTvShow(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id : Int = 0,
    val adult: Boolean,
    val backdrop_path: String? = null,
    var genre_ids: String,
    val original_language: String,
    val original_title: String? = null,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val name : String? = null,
    val first_air_date: String? = null
)
