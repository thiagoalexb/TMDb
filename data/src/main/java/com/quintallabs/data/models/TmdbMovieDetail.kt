package com.quintallabs.data.models

import com.google.gson.annotations.SerializedName

class TmdbMovieDetail (

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("genres")
    val tmdbGenres: List<TmdbGenre>,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("runtime")
    val runtime: Long,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("budget")
    val budget: Long,

    @SerializedName("revenue")
    val revenue: Long,



    )

