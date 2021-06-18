package com.dicoding.moviecatalog.data.repository

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.ResultData

interface Repository{
    suspend fun trendingMovie():ResultData<Discover?>
    suspend fun popularMovie():ResultData<Discover?>
    suspend fun topRateTvShow():ResultData<Discover?>
}
