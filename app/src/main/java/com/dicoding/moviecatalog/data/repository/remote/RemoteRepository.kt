package com.dicoding.moviecatalog.data.repository.remote

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.ResultData
import com.dicoding.moviecatalog.data.remote.services.RetrofitServices
import com.dicoding.moviecatalog.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val retrofitServices: RetrofitServices) :
    Repository {

    private suspend fun remote(remote:Response<Discover>) : ResultData<Discover?>{
        val request = withContext(Dispatchers.IO) { remote }

        return when(request.code()){
            200 -> ResultData(
                true,
                null,
                request.body()
            )

            else -> ResultData(
                false,
                request.body()?.status_message,
                null
            )
        }
    }

    override suspend fun trendingMovie(): ResultData<Discover?> {
        return remote(retrofitServices.discover().trendingMovieOfTheDay())
    }

    override suspend fun popularMovie(): ResultData<Discover?> {
        return remote(retrofitServices.discover().popularMovie())
    }

    override suspend fun topRateTvShow(): ResultData<Discover?> {
        return remote(retrofitServices.discover().topRateTvShow())
    }

    override suspend fun latestMovie(): ResultData<Discover?> {
        return remote(retrofitServices.discover().latestMovie())
    }

    override suspend fun latestTvShow(): ResultData<Discover?> {
        return remote(retrofitServices.discover().latestTvShow())
    }

}