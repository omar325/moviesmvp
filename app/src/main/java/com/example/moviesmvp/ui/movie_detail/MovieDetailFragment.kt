package com.example.moviesmvp.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviesmvp.R
import com.example.moviesmvp.domain.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_deatil.*

class MovieDetailFragment(
    private val movie: MovieItem
): Fragment() {

    companion object {
        val TAG = "MovieDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie_deatil, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.movie_title).text = movie.title
        view.findViewById<TextView>(R.id.release_date).text = movie.releaseDate
        view.findViewById<RatingBar>(R.id.rating_bar).rating = movie.voteAverage

        Picasso.get()
            .load("https://image.tmdb.org/t/p/original/${movie.posterPath?:""}")
            .fit().centerCrop()
            .into(view.findViewById<ImageView>(R.id.poster_thumbnail))

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}