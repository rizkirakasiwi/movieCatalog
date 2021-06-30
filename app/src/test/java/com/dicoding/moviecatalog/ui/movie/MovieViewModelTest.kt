package com.dicoding.moviecatalog.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.dummy.DiscoverDummy
import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import com.dicoding.moviecatalog.observeOnce
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var discoverRepository: DiscoverRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(discoverRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun trendingMovie() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(discoverRepository.trendingMovie()).thenReturn(DiscoverDummy.getDiscover())
        val trending = viewModel.trendingMovie()
        Mockito.verify(discoverRepository).trendingMovie()
        assertNotNull(trending)

        viewModel.popularMovie.observeOnce {
            assertNotNull(it)
            assertEquals(true, it?.total_pages!! > 0)
            assertEquals(true, it.total_results!! > 0)
            assertEquals(true, it.results.isNotEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addDiscover() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(discoverRepository.popularMovie()).thenReturn(DiscoverDummy.getDiscover())
        Mockito.`when`(discoverRepository.latestMovie()).thenReturn(DiscoverDummy.getDiscover())
        val discover = viewModel.addDiscover()
        Mockito.verify(discoverRepository).popularMovie()
        Mockito.verify(discoverRepository).latestMovie()
        assertNotNull(discover)
        viewModel.discover.observeOnce {
            assertEquals(true, it.isNotEmpty())
            assertEquals(true, it.size == 2)
        }
    }
}