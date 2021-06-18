package com.dicoding.moviecatalog.data.remote

import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.data.Discover
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverDao {
    @GET("movie/popular")
    suspend fun popularMovie(@Query("api_key") apiKey: String = BuildConfig.ApiKey) : Response<Discover>

    @GET("trending/movie/day")
    suspend fun trendingMovieOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): Response<Discover>

    @GET("tv/top_rated")
    suspend fun topRateTvShow(@Query("api_key") apiKey: String = BuildConfig.ApiKey):Response<Discover>
}