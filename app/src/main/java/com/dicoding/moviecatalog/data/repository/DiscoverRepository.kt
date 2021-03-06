package com.dicoding.moviecatalog.data.repository

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.ResultData

interface DiscoverRepository{
    suspend fun trendingMovie():ResultData<Discover?>
    suspend fun popularMovie():ResultData<Discover?>
    suspend fun topRateTvShow():ResultData<Discover?>
    suspend fun latestMovie():ResultData<Discover?>
    suspend fun latestTvShow():ResultData<Discover?>
    suspend fun tvPopular():ResultData<Discover?>
}
