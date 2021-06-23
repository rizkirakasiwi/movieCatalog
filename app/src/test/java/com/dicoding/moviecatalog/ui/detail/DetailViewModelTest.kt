package com.dicoding.moviecatalog.ui.detail

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup(){
        viewModel = DetailViewModel()
    }

    @Test
    fun testGetGenre() {
        val genre = viewModel.getGenre(listOf(16))
        Assert.assertNotNull(genre)
        Assert.assertEquals(genre,listOf("Animation"))
    }
}