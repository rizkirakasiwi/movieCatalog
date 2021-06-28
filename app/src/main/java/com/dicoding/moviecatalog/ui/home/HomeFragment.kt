package com.dicoding.moviecatalog.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.databinding.HomeFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
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

    private lateinit var binding : HomeFragmentBinding
    private lateinit var adapter : HomeItemAdapter

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
        binding.viewpagerHome.isUserInputEnabled = false
        val tabText = listOf(getString(R.string.movie), getString(R.string.tv_show))
        binding.viewpagerHome.adapter = HomeViewPagerAdapter(this)
        TabLayoutMediator(binding.tabHome, binding.viewpagerHome){tab, position ->
            tab.text = tabText[position]
        }.attach()
    }




}