package com.dicoding.moviecatalog.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dicoding.moviecatalog.MainCoroutineRule
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.data.repository.Repository
import com.dicoding.moviecatalog.observeOnce
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{

    private lateinit var viewModel : HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp(){
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun getDiscover() = mainCoroutineRule.runBlockingTest{
        viewModel.getPopularMovie()

        viewModel.popularMovie.observeOnce {
            assertNotNull(it)
            assertEquals(true, it?.total_pages!! > 0)
            assertEquals(true, it.total_results!! > 0)
            assertEquals(true, it.results.isNotEmpty())
        }
    }

    @Test
    fun getPopularMovie() = mainCoroutineRule.runBlockingTest {
            viewModel.getTrendingMovie()

            viewModel.trendingMovie.observeOnce {
                assertNotNull(it)
                assertEquals(true, it?.total_pages!! > 0)
                assertEquals(true, it.total_results!! > 0)
                assertEquals(true, it.results.isNotEmpty())
            }

    }

}