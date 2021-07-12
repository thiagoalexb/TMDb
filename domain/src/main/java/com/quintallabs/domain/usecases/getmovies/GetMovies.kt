package com.quintallabs.domain.usecases.getmovies

import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.Resource
import com.quintallabs.domain.repositories.MovieRepository

class GetMovies(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int) : Resource<List<Movie>> =
        movieRepository.getMovies(page)
}