package com.dicoding.moviecatalog.data

import com.dicoding.moviecatalog.ui.home.HomeItemChildAdapter

data class CategoryAdapterData(
    val title : String,
    val item : CategoryItemAdapterData
)

data class CategoryItemAdapterData(
    val adapter: HomeItemChildAdapter,
    val data: Discover?
)