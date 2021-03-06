package com.dicoding.moviecatalog.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Discover(
    val page: Int?,
    val results: List<Result?>,
    val total_pages: Int?,
    val total_results: Int?,
    val status_message : String?,
    val status_code : String?,
    var category : String? = null
):Parcelable

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String? = null,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String?,
    val title: String?,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val name : String? = null,
    val first_air_date: String? = null
):Parcelable
