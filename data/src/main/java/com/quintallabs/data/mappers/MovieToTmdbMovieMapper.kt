package com.quintallabs.data.mappers

import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbMovie
import com.quintallabs.domain.models.Movie
import javax.inject.Inject

class MovieToTmdbMovieMapper
@Inject
constructor() : Mapper<Movie, TmdbMovie> {

    override fun map(input: Movie): TmdbMovie =
        TmdbMovie(
            input.movieId,
            input.title,
            input.voteAverage,
            input.releaseDate,
            if (input.posterPath.isNullOrBlank()) "" else "${TmdbUrls.POSTER_URL}${input.posterPath}"
        )
}