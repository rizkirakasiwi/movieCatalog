package com.dicoding.moviecatalog.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.dummy.DiscoverDummy
import com.dicoding.moviecatalog.data.repository.Repository
import com.dicoding.moviecatalog.observeOnce
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun trendingMovie() = mainCoroutineRule.runBlockingTest {
        `when`(repository.trendingMovie()).thenReturn(DiscoverDummy.getDiscover())
        val trending = viewModel.trendingMovie()
        verify(repository).trendingMovie()
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
        `when`(repository.popularMovie()).thenReturn(DiscoverDummy.getDiscover())
        `when`(repository.topRateTvShow()).thenReturn(DiscoverDummy.getDiscover())
        `when`(repository.latestTvShow()).thenReturn(DiscoverDummy.getDiscover())
        `when`(repository.latestMovie()).thenReturn(DiscoverDummy.getDiscover())
        val discover = viewModel.addDiscover()
        verify(repository).popularMovie()
        verify(repository).topRateTvShow()
        verify(repository).latestTvShow()
        verify(repository).latestMovie()
        assertNotNull(discover)
        viewModel.discover.observeOnce {
            assertEquals(true, it.isNotEmpty())
            assertEquals(true, it.size == 4)
        }
    }

}