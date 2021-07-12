package com.quintallabs.data.di

import com.quintallabs.data.apis.TmdbApi
import com.quintallabs.data.mappers.TmdbMovieDetailToMovieDetailMapper
import com.quintallabs.data.mappers.TmdbMovieToMovieMapper
import com.quintallabs.data.mappers.base.ListMapperImp
import com.quintallabs.data.models.TmdbMovie
import com.quintallabs.data.repositories.TmdbMovieRepository
import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesMovieRepository(
        tmdbApi: TmdbApi,
        tmdbMovieToMovieMapper: TmdbMovieToMovieMapper,
        movieDetailMapper: TmdbMovieDetailToMovieDetailMapper
    ): MovieRepository =
        TmdbMovieRepository(
            tmdbApi,
            movieDetailMapper,
            tmdbMovieToMovieMapper
        )

}