package com.quintallabs.data.apis

import com.quintallabs.data.constants.TmdbUrls
import com.quintallabs.data.models.ApiResult
import com.quintallabs.data.models.TmdbMovie
import com.quintallabs.data.models.TmdbMovieDetail
import com.quintallabs.domain.models.Movie
import com.quintallabs.domain.models.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET(TmdbUrls.GET_MOVIE_UPCOMING_URL)
    suspend fun getMovies(@Query("page") page: Int): ApiResult<List<TmdbMovie>>

    @GET(TmdbUrls.GET_MOVIE_DETAIL_URL)
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): TmdbMovieDetail
}