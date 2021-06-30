package com.dicoding.moviecatalog.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.dummy.DiscoverDummy
import com.dicoding.moviecatalog.data.repository.DiscoverRepository
import com.dicoding.moviecatalog.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var discoverRepository: DiscoverRepository

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(discoverRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun trendingTvShow() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(discoverRepository.tvPopular()).thenReturn(DiscoverDummy.getDiscover())
        val trending = viewModel.trendingTvShow()
        Mockito.verify(discoverRepository).tvPopular()
        Assert.assertNotNull(trending)

        viewModel.popularMovie.observeOnce {
            Assert.assertNotNull(it)
            assertEquals(true, it?.total_pages!! > 0)
            assertEquals(true, it.total_results!! > 0)
            assertEquals(true, it.results.isNotEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addDiscover() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(discoverRepository.topRateTvShow()).thenReturn(DiscoverDummy.getDiscover())
        Mockito.`when`(discoverRepository.latestTvShow()).thenReturn(DiscoverDummy.getDiscover())
        val discover = viewModel.addDiscover()
        Mockito.verify(discoverRepository).topRateTvShow()
        Mockito.verify(discoverRepository).latestTvShow()
        Assert.assertNotNull(discover)
        viewModel.discover.observeOnce {
            assertEquals(true, it.isNotEmpty())
            assertEquals(true, it.size == 2)
        }
    }
}