package com.dicoding.moviecatalog.data.repository

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.ResultData

interface Repository{
    suspend fun discover():ResultData<Discover?>
    suspend fun popularMovie():ResultData<Discover?>
}
