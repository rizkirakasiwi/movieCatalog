package com.dicoding.moviecatalog.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.databinding.FavoriteFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }
    private lateinit var binding : FavoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpagerFav.isUserInputEnabled = false
        val tabText = listOf(getString(R.string.movie), getString(R.string.tv_show))
        binding.viewpagerFav.adapter = FavoriteViewPagerAdapter(this)
        TabLayoutMediator(binding.tabFav, binding.viewpagerFav){tab, position ->
            tab.text = tabText[position]
        }.attach()

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}