package com.quintallabs.data.mappers

import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.mappers.base.ListMapperImp
import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbGenre
import com.quintallabs.data.models.TmdbMovieDetail
import com.quintallabs.domain.models.Genre
import com.quintallabs.domain.models.MovieDetail
import javax.inject.Inject

class MovieDetailToTmdbMovieDetail
@Inject
constructor() : Mapper<MovieDetail, TmdbMovieDetail> {

    private val genreListMapper = ListMapperImp(GenreToTmdbGenreMapper())

    override fun map(input: MovieDetail): TmdbMovieDetail =
        TmdbMovieDetail(
            input.movieId,
            if (input.backdropPath.isNullOrBlank()) "" else "${TmdbUrls.POSTER_URL}${input.backdropPath}",
            genreListMapper.map(input.genres),
            input.title,
            input.overview,
            input.voteAverage,
            input.runtime,
            input.releaseDate,
            input.budget,
            input.revenue
        )
}