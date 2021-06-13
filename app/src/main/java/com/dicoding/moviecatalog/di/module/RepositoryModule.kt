package com.dicoding.moviecatalog.di.module

import com.dicoding.moviecatalog.data.repository.Repository
import com.dicoding.moviecatalog.data.repository.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun networkRepository(remoteRepository: RemoteRepository):Repository
}