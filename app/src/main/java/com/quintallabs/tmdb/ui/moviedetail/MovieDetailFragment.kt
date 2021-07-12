package com.quintallabs.tmdb.ui.moviedetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quintallabs.domain.models.Resource
import com.quintallabs.tmdb.R
import com.quintallabs.tmdb.databinding.FragmentMovieDetailBinding
import com.quintallabs.tmdb.databinding.FragmentUpcomingMoviesBinding
import com.quintallabs.tmdb.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    @Inject
    lateinit var requestManager: RequestManager

    private val viewModel: MovieDetailViewModel by viewModels()

    private lateinit var binding: FragmentMovieDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentMovieDetailBinding.bind(view)

        setObservables()

        val movieId = args.movieId

        viewModel.fetchMovieDetail(movieId)
    }

    private fun setObservables() {
        lifecycleScope.launch {

            viewModel.movieDetail.collect { resource ->

                setLoading(false)
                when (resource) {

                    is Resource.Success -> {

                        resource.data?.let {
                            binding.apply {
                                movieTitleValueTextView.text = it.title
                                movieOverviewValueTextView.text = it.overview
                                movieVoteAverageValueTextView.text = it.voteAverage.toString()
                                movieReleaseDateValueTextView.text = it.releaseDate
                                movieRuntimeValueTextView.text = it.runtime.toString()
                                movieBudgetValueTextView.text = it.budget.toString()
                                movieRevenueValueTextView.text = it.revenue.toString()
                                movieGenresValueTextView.text = it.genres.joinToString (separator = ", ") { it.name }

                                it.backdropPath.let {
                                    requestManager.load(it)
                                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                                        .into(movieBackdropImageView)
                                }
                            }
                        } ?: run {
                            Toast.makeText(
                                context,
                                getString(R.string.something_wrong),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    is Resource.Error -> {

                        Toast.makeText(
                            context,
                            getString(R.string.something_wrong),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is Resource.Loading -> {
                        setLoading(true)
                    }
                }
            }
        }

    }

}