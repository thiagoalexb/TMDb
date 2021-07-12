package com.quintallabs.tmdb.ui.upcomingmovies

import androidx.recyclerview.widget.DiffUtil
import com.quintallabs.domain.models.Movie

object MovieComparator: DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}