package com.dicoding.moviecatalog.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.moviecatalog.EspressoIdlingResource
import com.dicoding.moviecatalog.R
import com.dicoding.moviecatalog.data.Result
import com.dicoding.moviecatalog.databinding.DetailFragmentBinding
import com.dicoding.moviecatalog.util.DateHelper
import com.dicoding.moviecatalog.util.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(),  View.OnClickListener {

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

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            result = arguments?.getParcelable("data")!!
            Log.i("DetailFragment", result.toString())
        }



        if (::result.isInitialized){
            EspressoIdlingResource.increment()
            val job = GlobalScope.launch(Dispatchers.Main){
                if (result.title == null){
                    viewModel.selectFavoriteTvShow(result.id)
                }else{
                    viewModel.selectFavorite(result.id)
                }
            }

            job.invokeOnCompletion {
                EspressoIdlingResource.decrement()
            }
        }

        binding.backButton.setOnClickListener(this)
        binding.favButton.setOnClickListener(this)
    }

    @DelicateCoroutinesApi
    override fun onResume() {
        super.onResume()
        viewModel.isFavorite.observe(viewLifecycleOwner, {
            if (::result.isInitialized) {
                if (it) {
                    binding.favButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_24
                        )
                    )
                } else {
                    binding.favButton.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_favorite_border_24
                        )
                    )
                }
            }
        })


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

    @DelicateCoroutinesApi
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.back_button -> activity?.onBackPressed()
            R.id.fav_button -> {
                EspressoIdlingResource.increment()
                val job = GlobalScope.launch(Dispatchers.IO){
                    if (::result.isInitialized) {
                        if (!viewModel.isFavorite.value!!){
                            Log.i("DetailFragment", "insert")
                            if (result.title == null){
                                viewModel.addFavoriteTvShow(viewModel.discoverTvShow(result))
                            }else {
                                viewModel.addFavorite(viewModel.discover(result))
                            }
                        }else{
                            Log.i("DetailFragment", "delete")
                            if (result.title == null) {
                                viewModel.deleteFavoriteTvShow(movieId = result.id)
                            }else{
                                viewModel.deleteFavorite(movieId = result.id)
                            }
                        }
                    }
                }

                job.invokeOnCompletion {
                    EspressoIdlingResource.decrement()
                }
            }
        }
    }

}