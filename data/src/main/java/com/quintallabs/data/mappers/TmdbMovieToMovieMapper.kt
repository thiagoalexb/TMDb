package com.quintallabs.data.mappers

import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbMovie
import com.quintallabs.domain.models.Movie
import javax.inject.Inject

class TmdbMovieToMovieMapper
@Inject
constructor() : Mapper<TmdbMovie, Movie> {

    override fun map(input: TmdbMovie): Movie =
        Movie(
            input.id,
            input.title,
            input.voteAverage,
            input.releaseDate,
            if (input.posterPath.isNullOrBlank()) "" else "${TmdbUrls.POSTER_URL}${input.posterPath}"
        )
}