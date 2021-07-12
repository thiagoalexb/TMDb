package com.quintallabs.domain.models


class MovieDetail(
    val movieId: Int,
    val backdropPath: String,
    val genres: List<Genre>,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val runtime: Long,
    val releaseDate: String,
    val budget: Long,
    val revenue: Long
)

