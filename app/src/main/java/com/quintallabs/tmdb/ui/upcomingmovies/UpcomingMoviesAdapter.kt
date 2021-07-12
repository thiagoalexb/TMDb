package com.quintallabs.tmdb.ui.upcomingmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.quintallabs.domain.models.Movie
import com.quintallabs.tmdb.databinding.ItemMovieBinding

class UpcomingMoviesAdapter(
    private val requestManager: RequestManager,
    diffCallback: DiffUtil.ItemCallback<Movie>,
    private val commands: MoviesCommand
) :
    PagingDataAdapter<Movie, UpcomingMoviesAdapter.MovieViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val itemMovie = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(itemMovie, requestManager, commands)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        movie?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(
        private val itemMovieBinding: ItemMovieBinding,
        private val requestManager: RequestManager,
        private val commands: MoviesCommand
    ) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) {

            itemMovieBinding.movieCardView.setOnClickListener(null)
            itemMovieBinding.movieTitleTextView.text = movie.title
            itemMovieBinding.movieReleaseDateValueTextView.text = movie.releaseDate
            itemMovieBinding.movieVoteAverageTextView.text = movie.voteAverage.toString()
            itemMovieBinding.movieCardView.setOnClickListener {
                commands.navigateToDetailCommand(movie.movieId)
            }

            movie.posterPath?.let {
                requestManager.load(it)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(itemMovieBinding.moviePosterImageView)
            }
        }
    }

    interface MoviesCommand {

        fun navigateToDetailCommand(movieId: Int)
    }
}