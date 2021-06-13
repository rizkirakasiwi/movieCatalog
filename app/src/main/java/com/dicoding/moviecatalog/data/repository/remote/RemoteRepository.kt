package com.dicoding.moviecatalog.data.repository.remote

import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.ResultData
import com.dicoding.moviecatalog.data.remote.services.RetrofitServices
import com.dicoding.moviecatalog.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val retrofitServices: RetrofitServices) :
    Repository {

    override suspend fun discover(): ResultData<Discover?> {
        val request = withContext(Dispatchers.IO) {
            retrofitServices.discover().discover()
        }

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

    override suspend fun popularMovie(): ResultData<Discover?> {
        val request = withContext(Dispatchers.IO){
            retrofitServices.discover().trandingMovieOfTheDay()
        }

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

}