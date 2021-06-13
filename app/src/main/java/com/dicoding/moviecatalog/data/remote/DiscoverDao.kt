package com.dicoding.moviecatalog.data.remote

import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.data.Discover
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverDao {
    @GET("discover/movie")
    suspend fun discover(
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): Response<Discover>

    @GET("trending/movie/day")
    suspend fun trandingMovieOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): Response<Discover>
}