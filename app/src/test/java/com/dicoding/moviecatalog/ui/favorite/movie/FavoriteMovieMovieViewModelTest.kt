package com.dicoding.moviecatalog.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.local.entity.FavoriteMovie
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import com.dicoding.moviecatalog.observeOnce
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieMovieViewModelTest {
    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var discoverRepository: FavoriteRepository

    @Mock
    private lateinit var pagedList: PagedList<FavoriteMovie>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(discoverRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadFavorite() = mainCoroutineRule.runBlockingTest {
        val dummyFav = pagedList
        `when`(dummyFav.size).thenReturn(5)
        val fav = MutableLiveData<PagedList<FavoriteMovie>>()
        fav.value = dummyFav

        `when`(discoverRepository.selectMovie()).thenReturn(fav)
        viewModel.favorite.observeOnce {
            assertNotNull(it)
            assertEquals(5, it.size)
        }
        verify(discoverRepository).selectMovie()

    }
}