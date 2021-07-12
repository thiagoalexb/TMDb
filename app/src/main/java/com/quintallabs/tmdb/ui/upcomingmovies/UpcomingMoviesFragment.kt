package com.quintallabs.tmdb.ui.upcomingmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.bumptech.glide.RequestManager
import com.quintallabs.domain.models.Resource
import com.quintallabs.tmdb.R
import com.quintallabs.tmdb.databinding.FragmentUpcomingMoviesBinding
import com.quintallabs.tmdb.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@InternalCoroutinesApi
@AndroidEntryPoint
class UpcomingMoviesFragment : BaseFragment(R.layout.fragment_upcoming_movies), UpcomingMoviesAdapter.MoviesCommand {

    @Inject
    lateinit var requestManager: RequestManager

    private val viewModel: UpcomingMoviesViewModel by viewModels()

    private lateinit var binding: FragmentUpcomingMoviesBinding

    private lateinit var movieAdapter: UpcomingMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUpcomingMoviesBinding.bind(view)

        setElements()

        setObservables()

        viewModel.fetchMovies(1)
    }

    private fun setElements() {

        movieAdapter = UpcomingMoviesAdapter(requestManager, MovieComparator, this)

        binding.apply {
            moviesRecyclerView.adapter = movieAdapter
        }
    }

    private fun setObservables() {

        lifecycleScope.launchWhenCreated {

            viewModel.movies.collect { resource ->

                setLoading(false)

                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let {
                            movieAdapter.submitData(PagingData.from(it))
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

    override fun navigateToDetailCommand(movieId: Int) {

        findNavController().navigate(UpcomingMoviesFragmentDirections.actionUpcomingMoviesFragmentToMovieDetailFragment(movieId))
    }
}