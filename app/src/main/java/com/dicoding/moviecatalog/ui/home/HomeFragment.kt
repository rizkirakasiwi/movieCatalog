package com.dicoding.moviecatalog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.CategoryAdapterData
import com.dicoding.moviecatalog.data.CategoryItemAdapterData
import com.dicoding.moviecatalog.databinding.HomeFragmentBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding : HomeFragmentBinding
    private val groupieAdapter = GroupieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EspressoIdlingResource.increment()
        val job = GlobalScope.launch(Dispatchers.Main){
            viewModel.getTrendingMovie()
            viewModel.getPopularMovie()
        }

        job.invokeOnCompletion {
            EspressoIdlingResource.decrement()
        }

    }

    override fun onResume() {
        super.onResume()
        setTrendingMovie()
        setPopularMovie()

        getAdapter()
    }

    private fun getAdapter(){
        viewModel.adapterGroup.observe(viewLifecycleOwner, {
            groupieAdapter.addAll(it)
            binding.rvHome.adapter = groupieAdapter
        })
    }

    private fun setPopularMovie(){
        viewModel.popularMovie.observe(viewLifecycleOwner, {
           viewModel.addAdapter(HomeItemAdapter(getString(R.string.popular_movie), it))
        })
    }

    private fun setTrendingMovie(){
        val adapter = BannerAdapter()
        viewModel.trendingMovie.observe(viewLifecycleOwner, {
            if (it?.results != null) {
                adapter.submitList(it.results.subList(0, 5))
            }

            binding.rvBannerPopular.adapter = adapter
            binding.rvBannerPopular.setInfinite(true)
            binding.rvBannerPopular.set3DItem(true)
        })
    }


}