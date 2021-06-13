package com.dicoding.moviecatalog.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularMovie()
    }

    override fun onResume() {
        super.onResume()
        val adapter = BannerAdapter()
        viewModel.popularMovie.observe(viewLifecycleOwner, {
            if (it?.results != null)
                adapter.submitList(it.results.subList(0, 5))

            binding.bannerPopular.adapter = adapter
            binding.bannerPopular.setInfinite(true)
            binding.bannerPopular.set3DItem(true)
        })


    }



}