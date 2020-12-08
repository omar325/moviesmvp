package com.example.moviesmvp.ui.movie_list

import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieCategory.LATEST
import com.example.moviesmvp.domain.MovieItem
import com.example.moviesmvp.infrastructure.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

open class MovieListInteractor(
    private val movieRepository: MovieRepository
) {
    interface OnMovieFetchFinishedListener {
        fun onMovieListSuccess(movies: List<MovieItem>)
        fun onMovieListError(exception: Exception)
    }

    open fun fetchMoviesByCategory(
        forceOnline: Boolean = false,
        category: MovieCategory,
        fetchFinishedListener: OnMovieFetchFinishedListener,
        lifeCycleScope: CoroutineScope
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val movies = movieRepository.getMoviesByCategory(forceOnline, category)
                lifeCycleScope.launch(Dispatchers.Main) {
                    fetchFinishedListener.onMovieListSuccess(movies)
                }
            } catch (exception: Exception) {
                lifeCycleScope.launch(Dispatchers.Main) {
                    fetchFinishedListener.onMovieListError(exception)
                }
            }
        }
    }

}