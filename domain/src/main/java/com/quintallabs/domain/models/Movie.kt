package com.quintallabs.domain.models


class Movie(
    val movieId: Int,
    val title: String,
    val voteAverage: Double,
    val releaseDate: String?,
    val posterPath: String?,
) {

    override fun equals(other: Any?): Boolean {
        return other is Movie
                && movieId == other.movieId
                && title == other.title
                && voteAverage == other.voteAverage
                && releaseDate == other.releaseDate
                && posterPath == other.posterPath
    }

    override fun hashCode(): Int {
        var result = movieId
        result = 31 * result + title.hashCode()
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (posterPath?.hashCode() ?: 0)
        return result
    }
}