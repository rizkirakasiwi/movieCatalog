package com.dicoding.moviecatalog.util

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.dicoding.moviecatalog.BuildConfig
import com.dicoding.moviecatalog.R
import kotlinx.coroutines.*

@DelicateCoroutinesApi
fun ImageView.load(url:String){
    val imageUrl = BuildConfig.imagePoster
    Log.i("ImageLoader", "Load image from $imageUrl$url")
    GlobalScope.launch(Dispatchers.Main){
        val image = withContext(Dispatchers.IO){
            GlideApp.with(this@load).load(imageUrl+url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_error)
                .override(Target.SIZE_ORIGINAL)
        }

        image.into(this@load)
    }
}

