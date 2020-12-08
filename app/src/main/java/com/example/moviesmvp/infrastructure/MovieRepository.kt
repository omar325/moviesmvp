package com.example.moviesmvp.infrastructure

import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem

class MovieRepository(
    private val localRepository: Repository,
    private val remoteRepository: Repository
) {
    suspend fun getMoviesByCategory(forceOnline: Boolean, category: MovieCategory): List<MovieItem> {
        if(forceOnline) {
            return fetchOnlineByCategory(category)
        }
        var movies = localRepository.getMoviesByCategory(category.path).results
        if(movies.isNotEmpty()) {
            return movies
        }
        return fetchOnlineByCategory(category)
    }

    private suspend fun fetchOnlineByCategory(category: MovieCategory): List<MovieItem> {
        val movies = remoteRepository.getMoviesByCategory(category.path).results
        for (movie in movies) {
            movie.category = category.path
            localRepository.saveMovie(movie)
        }
        return movies
    }
}