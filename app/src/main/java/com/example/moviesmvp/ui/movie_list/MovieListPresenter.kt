package com.example.moviesmvp.ui.movie_list

import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception

open class MovieListPresenter(
    private val movieListView: MovieListView,
    private val movieInteractor: MovieListInteractor,
    private val lifeCycleScope: CoroutineScope
): MovieListInteractor.OnMovieFetchFinishedListener {

    fun fetchMoviesByCategory(category: MovieCategory) {
        movieListView.showProgressBar(true)
        movieInteractor.fetchMoviesByCategory(false, category, this@MovieListPresenter, lifeCycleScope)
    }

    fun refresh(category: MovieCategory) {
        movieListView.showProgressBar(false)
        movieInteractor.fetchMoviesByCategory(true, category, this@MovieListPresenter, lifeCycleScope)
    }

    override fun onMovieListSuccess(movies: List<MovieItem>) {
        movieListView.showProgressBar(false)
        movieListView.updateList(movies)
    }

    override fun onMovieListError(exception: Exception) {
        movieListView.showProgressBar(false)
        movieListView.showErrorMessage("An error occurred while fetching the data")
        //movieListView.showErrorMessage(exception.message?:"")
    }
}