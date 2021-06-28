package com.dicoding.moviecatalog.ui.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.databinding.TvShowFragmentBinding
import com.dicoding.moviecatalog.ui.home.BannerAdapter
import com.dicoding.moviecatalog.ui.home.HomeItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    companion object {
        fun newInstance() = TvShowFragment()
    }

    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var binding : TvShowFragmentBinding
    private lateinit var adapter : HomeItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeItemAdapter()
        EspressoIdlingResource.increment()
        val job = GlobalScope.launch(Dispatchers.Main){
            viewModel.addDiscover()
            viewModel.trendingTvShow()
        }

        job.invokeOnCompletion {
            EspressoIdlingResource.decrement()
        }

        setTrendingMovie()
        getAdapter()
    }

    private fun getAdapter(){
        viewModel.discover.observe(viewLifecycleOwner, {
            it[0]?.category = getString(R.string.top_rate_tvshow)
            it[1]?.category = getString(R.string.latest_tvshow)
            Log.i("HomeFragment", it.size.toString())
            adapter.submitList(it)
            binding.rvHome.adapter = adapter
        })
    }

    private fun setTrendingMovie(){
        val adapter = BannerAdapter()
         viewModel.trendingTvShow.observe(viewLifecycleOwner, {
            if (it?.results != null) {
                adapter.submitList(it.results.subList(0, 5))
            }

            binding.rvBannerPopular.adapter = adapter
            binding.rvBannerPopular.setInfinite(true)
            binding.rvBannerPopular.set3DItem(true)
        })
    }
}