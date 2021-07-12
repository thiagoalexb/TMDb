package com.quintallabs.domain.repositories

import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.MovieDetail
import com.quintallabs.domain.models.Resource

interface MovieRepository {

    suspend fun getMovies(page: Int): Resource<List<Movie>>

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail?>
}