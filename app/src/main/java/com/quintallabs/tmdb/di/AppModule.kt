package com.quintallabs.tmdb.di

import android.app.Application
import com.bumptech.glide.Glide
import com.quintallabs.data.repositories.TmdbMovieRepository
import com.quintallabs.domain.repositories.MovieRepository
import com.quintallabs.domain.usecases.getmoviedetail.GetMovieDetail
import com.quintallabs.domain.usecases.getmovies.GetMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesGlide(context: Application) =
        Glide.with(context)

    @Provides
    fun providesGetMovies(movieRepository: TmdbMovieRepository) =
        GetMovies(movieRepository)

    @Provides
    fun providesGetMovieDetail(movieRepository: TmdbMovieRepository) =
        GetMovieDetail(movieRepository)

}