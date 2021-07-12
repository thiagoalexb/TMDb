package com.quintallabs.tmdb.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.MovieDetail
import com.quintallabs.domain.models.Resource
import com.quintallabs.domain.usecases.getmoviedetail.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val getMovieDetail: GetMovieDetail
) : ViewModel() {

    private val _movieDetail = MutableSharedFlow<Resource<MovieDetail?>>()
    val movieDetail = _movieDetail.asSharedFlow()

    fun fetchMovieDetail(movieId: Int) {

        viewModelScope.launch {
            val movieDetail = getMovieDetail(movieId)
            _movieDetail.emit(movieDetail)
        }
    }
}