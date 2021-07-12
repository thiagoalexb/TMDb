package com.quintallabs.tmdb.ui.upcomingmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.Resource
import com.quintallabs.domain.usecases.getmovies.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel
@Inject
constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    private val _movies = MutableSharedFlow<Resource<List<Movie>>>()
    val movies = _movies.asSharedFlow()

    fun fetchMovies(page: Int) {

        viewModelScope.launch {

            _movies.emit(Resource.Loading())
            val upcomingMovies = getMovies(page)
            _movies.emit(upcomingMovies)
        }
    }
}