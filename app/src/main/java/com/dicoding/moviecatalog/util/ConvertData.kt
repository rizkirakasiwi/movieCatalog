package com.dicoding.moviecatalog.util

import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.data.genre
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow

fun FavoriteMovie.toResult():Result{

    val genreList = mutableSetOf<Int>()
    val mgenre = genre_ids.split(",").map { it.trim() }
    mgenre.forEach {
        val ngenre = genre.filterValues { value -> value == it }.keys
        genreList.addAll(ngenre)
    }

    return Result(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genreList.toList(),
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        name = name,
        first_air_date = first_air_date
    )
}

fun FavoriteTvShow.toResult():Result{

    val genreList = mutableSetOf<Int>()
    val mgenre = genre_ids.split(",").map { it.trim() }
    mgenre.forEach {
        val ngenre = genre.filterValues { value -> value == it }.keys
        genreList.addAll(ngenre)
    }

    return Result(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genreList.toList(),
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,
        name = name,
        first_air_date = first_air_date
    )
}