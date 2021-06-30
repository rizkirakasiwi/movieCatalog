package com.dicoding.moviecatalog.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.databinding.FavoriteTvShowFragmentBinding
import com.dicoding.moviecatalog.ui.favorite.movie.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTvShowFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteTvShowFragment()
    }

    private val viewModel: FavoriteTvShowViewModel by viewModels()
    private lateinit var binding : FavoriteTvShowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteTvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onResume() {
        super.onResume()
        val adapter = FavoriteTvShowAdapter()

        viewModel.favorite.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.favRecyclerview.adapter = adapter
        })
    }

}