package com.dicoding.moviecatalog.data

data class ResultData<T>(
    val isSuccess:Boolean,
    val message:String?,
    val data:T?
)