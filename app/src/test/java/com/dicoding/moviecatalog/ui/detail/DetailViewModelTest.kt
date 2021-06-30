package com.dicoding.moviecatalog.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.dummy.DiscoverDummy
import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import com.dicoding.moviecatalog.data.repository.FavoriteRepository
import com.dicoding.moviecatalog.observeOnce
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Before
    fun setup(){
        viewModel = DetailViewModel(favoriteRepository)
    }

    @Test
    fun testGetGenre() {
        val genre = viewModel.getGenre(listOf(16))
        Assert.assertNotNull(genre)
        Assert.assertEquals(genre,listOf("Animation"))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addAndDeleteFavoriteTest() = mainCoroutineRule.runBlockingTest{
        viewModel.addFavorite(DiscoverDummy.getFavorite())

        viewModel.isFavorite.observeOnce {
            assertEquals(true, it)
        }

        viewModel.deleteFavorite(DiscoverDummy.getFavorite().id)

        viewModel.isFavorite.observeOnce {
            assertEquals(false, it)
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun selectFavoriteTest() = mainCoroutineRule.runBlockingTest {
        `when`(favoriteRepository.selectMovie(DiscoverDummy.getFavorite().id)).thenReturn(listOf(DiscoverDummy.getFavorite()))
        val select = viewModel.selectFavorite(DiscoverDummy.getFavorite().id)
        verify(favoriteRepository).selectMovie(DiscoverDummy.getFavorite().id)
        assertNotNull(select)

        viewModel.isFavorite.observeOnce {
            assertEquals(true, it)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun addAndDeleteFavoriteTvShowTest() = mainCoroutineRule.runBlockingTest{
        viewModel.addFavoriteTvShow(DiscoverDummy.getFavoriteTvShow())

        viewModel.isFavorite.observeOnce {
            assertEquals(true, it)
        }

        viewModel.deleteFavoriteTvShow(DiscoverDummy.getFavoriteTvShow().id)

        viewModel.isFavorite.observeOnce {
            assertEquals(false, it)
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun selectFavoriteTvShowTest() = mainCoroutineRule.runBlockingTest {
        `when`(favoriteRepository.selectTvShow(DiscoverDummy.getFavoriteTvShow().id)).thenReturn(listOf(DiscoverDummy.getFavoriteTvShow()))
        val select = viewModel.selectFavoriteTvShow(DiscoverDummy.getFavoriteTvShow().id)
        verify(favoriteRepository).selectTvShow(DiscoverDummy.getFavoriteTvShow().id)
        assertNotNull(select)

        viewModel.isFavorite.observeOnce {
            assertEquals(true, it)
        }
    }
}