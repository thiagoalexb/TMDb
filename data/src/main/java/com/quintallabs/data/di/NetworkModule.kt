package com.quintallabs.data.di

import com.quintallabs.data.apis.TmdbApi
import com.quintallabs.data.constants.TmdbUrls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(TmdbUrls.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideAddressApi(retrofit: Retrofit): TmdbApi =
        retrofit.create(TmdbApi::class.java)
}