package com.dicoding.moviecatalog.di.module

import com.dicoding.moviecatalog.data.remote.services.RetrofitServices
import com.dicoding.moviecatalog.data.remote.services.RetrofitServicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RetrofitModule {
    @Binds
    abstract fun retrofitServices(retrofitServicesImpl: RetrofitServicesImpl):RetrofitServices
}