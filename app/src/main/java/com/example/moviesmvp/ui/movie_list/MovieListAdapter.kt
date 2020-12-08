package com.example.moviesmvp.ui.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesmvp.R
import com.example.moviesmvp.domain.MovieItem
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val onMovieClickAction: (selectedMovie: MovieItem) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val movieList = AsyncListDiffer<MovieItem>(
        this,
        object: DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.isSameAs(newItem)

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.hasSameContentAs(newItem)
        })

    fun submitList(data: List<MovieItem>) =
        movieList.submitList(data)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : MovieViewHolder = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.currentList[position], onMovieClickAction)
    }

    override fun getItemCount(): Int =
        movieList.currentList.size

    class MovieViewHolder(
        private val view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(
            movie: MovieItem,
            onMovieClickAction: (movieClicked: MovieItem) -> Unit
        ) {
            view.setOnClickListener {
                onMovieClickAction(movie)
            }
            view.findViewById<TextView>(R.id.movie_title).text = movie.title
            view.findViewById<TextView>(R.id.release_date).text = movie.releaseDate
            view.findViewById<RatingBar>(R.id.rating_bar).rating = movie.voteAverage

            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/${movie.posterPath?:""}")
                .fit().centerCrop()
                .into(view.findViewById<ImageView>(R.id.poster_thumbnail))
        }
    }
}