package com.dicoding.moviecatalog.data.repository.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.moviecatalog.data.local.dao.FavoriteDao
import com.dicoding.moviecatalog.data.local.database.FavoriteDatabase
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.local.entity.FavoriteTvShow
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalFavoriteRepository @Inject constructor(
    @ApplicationContext context: Context
):FavoriteRepository {
    private lateinit var favoriteDao: FavoriteDao

    init {
        val db = FavoriteDatabase.getDatabase(context)
        favoriteDao = db.favoriteDao()
    }

    override suspend fun insertMovie(favoriteMovie: FavoriteMovie) {
        favoriteDao.insertMovie(favoriteMovie)
    }

    override suspend fun deleteMovie(movieId : Int) {
        favoriteDao.deleteMovie(movieId)
    }

    override suspend fun selectMovie(movieId: Int): List<FavoriteMovie> {
        return withContext(Dispatchers.IO) {
            favoriteDao.selectMovie(movieId)
        }
    }

    override suspend fun selectMovie():  LiveData<PagedList<FavoriteMovie>> {
        return withContext(Dispatchers.IO){
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build()

            LivePagedListBuilder(favoriteDao.selectMovie(), config).build()
        }
    }

    override suspend fun insertTvShow(favoriteTvShow: FavoriteTvShow) {
        favoriteDao.insertTvShow(favoriteTvShow)
    }

    override suspend fun deleteTvShow(movieId: Int) {
        favoriteDao.deleteTvshow(movieId)
    }

    override suspend fun selectTvShow(movieId: Int): List<FavoriteTvShow> {
        return withContext(Dispatchers.IO){
            favoriteDao.selectTvShow(movieId)
        }
    }

    override suspend fun selectTvShow(): LiveData<PagedList<FavoriteTvShow>> {
        return withContext(Dispatchers.IO){
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build()

            LivePagedListBuilder(favoriteDao.selectTvShow(), config).build()
        }
    }

}