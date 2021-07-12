package com.quintallabs.data.mappers

import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.mappers.base.ListMapperImp
import com.quintallabs.data.mappers.base.Mapper
import com.quintallabs.data.models.TmdbMovieDetail
import com.quintallabs.domain.models.MovieDetail
import javax.inject.Inject

class TmdbMovieDetailToMovieDetailMapper
@Inject
constructor() : Mapper<TmdbMovieDetail, MovieDetail> {

    private val genreListMapper = ListMapperImp(TmdbGenreToGenreMapper())

    override fun map(input: TmdbMovieDetail): MovieDetail =
        MovieDetail(
            input.movieId,
            if (input.backdropPath.isNullOrBlank()) "" else "${TmdbUrls.POSTER_URL}${input.backdropPath}",
            genreListMapper.map(input.tmdbGenres),
            input.title,
            input.overview,
            input.voteAverage,
            input.runtime,
            input.releaseDate,
            input.budget,
            input.revenue
        )
}