package com.dicoding.moviecatalog.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.databinding.DetailFragmentBinding
import com.dicoding.moviecatalog.util.DateHelper
import com.dicoding.moviecatalog.util.load
import kotlinx.coroutines.DelicateCoroutinesApi

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding : DetailFragmentBinding
    private lateinit var result: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            result = arguments?.getParcelable("data")!!
            Log.i("DetailFragment", result.toString())
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    @DelicateCoroutinesApi
    override fun onResume() {
        super.onResume()
        initialize()
    }

    @DelicateCoroutinesApi
    fun initialize(){
        if(::result.isInitialized) {
            binding.backdropImage.load(result.backdrop_path)
            binding.descriptionText.text = result.overview
            binding.ratingText.text = result.vote_average.toString()
            binding.ratingbarDetail.rating = result.vote_average.toFloat() / 2f
            binding.titleText.text = result.title ?: result.name
            val genre = viewModel.getGenre(result.genre_ids).joinToString()
            if (result.release_date != null) {
                val year: String? = DateHelper.formatDate(result.release_date)
                binding.genreYearText.text = getString(R.string.genre_year, genre, year)
            }else
                binding.genreYearText.text = genre
        }
    }

}