package com.quintallabs.data.constants

import com.quintallabs.data.BuildConfig

object TmdbUrls {

    private const val DEFAULT_QUERY_DETAIL =
        "?api_key=${BuildConfig.API_KEY}&language=${BuildConfig.LANGUAGE}"

    private const val DEFAULT_QUERY_UPCOMING =
        "$DEFAULT_QUERY_DETAIL&region=${BuildConfig.REGION}"

    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val GET_MOVIE_UPCOMING_URL = "movie/upcoming$DEFAULT_QUERY_UPCOMING"
    const val GET_MOVIE_DETAIL_URL = "movie/{movie_id}$DEFAULT_QUERY_DETAIL"

    const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
}