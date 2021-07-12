package com.quintallabs.domain.usecases.getmoviedetail

import com.quintallabs.domain.repositories.MovieRepository

class GetMovieDetail(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int) =
        movieRepository.getMovieDetail(movieId)
}