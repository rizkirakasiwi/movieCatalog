package com.dicoding.moviecatalog.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.databinding.FavoriteMovieFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteMovieFragment()
    }

    private val viewModel: FavoriteMovieViewModel by viewModels()
    private lateinit var binding : FavoriteMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoriteMovieFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        val adapter = FavoriteAdapter()
        viewModel.favorite.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.favRecyclerview.adapter = adapter
        })
    }
}