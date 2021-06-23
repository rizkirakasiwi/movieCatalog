package com.dicoding.moviecatalog.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.moviecatalog.data.genre

class DetailViewModel : ViewModel() {
    fun getGenre(value:List<Int>):List<String?>{
        val list = mutableListOf<String?>()
        value.forEach {
            list.add(genre[it])
        }
        return list
    }
}