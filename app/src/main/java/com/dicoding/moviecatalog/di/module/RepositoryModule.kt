package com.dicoding.moviecatalog.di.module

import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import com.dicoding.moviecatalog.data.repository.remote.RemoteDiscoverRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun networkRepository(remoteRepository: RemoteDiscoverRepository): DiscoverRepository
}