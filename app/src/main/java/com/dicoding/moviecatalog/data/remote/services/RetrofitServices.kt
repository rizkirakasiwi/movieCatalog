package com.dicoding.moviecatalog.data.remote.services

import com.dicoding.moviecatalog.data.remote.DiscoverDao
import retrofit2.Retrofit
import javax.inject.Inject

interface RetrofitServices {
    fun discover(): DiscoverDao
}

class RetrofitServicesImpl @Inject constructor(private val retrofit: Retrofit):RetrofitServices{
    override fun discover(): DiscoverDao {
        return retrofit.create(DiscoverDao::class.java)
    }
}