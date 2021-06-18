package com.dicoding.moviecatalog.ui.home

import android.util.Log
import android.view.View
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Discover
import com.dicoding.moviecatalog.databinding.RvHomeLayoutBinding
import com.xwray.groupie.viewbinding.BindableItem

class HomeItemAdapter(private val title:String, private val data:Discover?):BindableItem<RvHomeLayoutBinding>() {
    override fun bind(viewBinding: RvHomeLayoutBinding, position: Int) {
        Log.i("HomeItemAdapter", "You see me")
        val popularMovieAdapter = HomeItemChildAdapter()

        popularMovieAdapter.submitList(data?.results)
        viewBinding.titleText.text = title
        viewBinding.rvHomeLayout.adapter = popularMovieAdapter
    }

    override fun getLayout(): Int {
        return R.layout.rv_home_layout
    }

    override fun initializeViewBinding(view: View): RvHomeLayoutBinding {
        return RvHomeLayoutBinding.bind(view)
    }

}
