package com.quintallabs.data.repositories

import com.quintallabs.data.apis.TmdbApi
import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.mappers.TmdbMovieDetailToMovieDetailMapper
import com.quintallabs.data.mappers.TmdbMovieToMovieMapper
import com.quintallabs.data.mappers.base.ListMapper
import com.quintallabs.data.mappers.base.ListMapperImp
import com.quintallabs.data.models.TmdbMovie
import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.MovieDetail
import com.quintallabs.domain.models.Resource
import com.quintallabs.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TmdbMovieRepository
@Inject
constructor(
    private val tmdbApi: TmdbApi,
    private val movieDetailMapper: TmdbMovieDetailToMovieDetailMapper,
    private val movieMapper: TmdbMovieToMovieMapper,
) : MovieRepository {

    private val movieListMapper = ListMapperImp(movieMapper)

    override suspend fun getMovies(page: Int): Resource<List<Movie>> {

        return try {
            withContext(Dispatchers.IO) {

                val movies = tmdbApi.getMovies(page)

                val map = movieListMapper.map(movies.results)

                Resource.Success(map)
            }
        } catch (throwable: Throwable) {
            Resource.Error("")
        }

    }

    override suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail?> {

        return try {
            withContext(Dispatchers.IO) {

                val movie = tmdbApi.getMovieDetail(movieId)

                val map = movieDetailMapper.map(movie)

                Resource.Success(map)
            }
        } catch (throwable: Throwable) {
            Resource.Error("")
        }
    }
}