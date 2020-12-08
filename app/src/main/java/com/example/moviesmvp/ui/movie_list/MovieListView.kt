package com.example.moviesmvp.ui.movie_list

import com.example.moviesmvp.domain.MovieItem

interface MovieListView {
    fun showProgressBar(show: Boolean)
    fun updateList(movies: List<MovieItem>)
    fun showErrorMessage(message: String)
}