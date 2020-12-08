package com.example.moviesmvp.infrastructure.room

import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem
import com.example.moviesmvp.domain.ApiResponse
import com.example.moviesmvp.infrastructure.Repository

class RoomRepository(
    private val movieDao: MovieDao
): Repository {
    override suspend fun getMoviesByCategory(category: String): ApiResponse {
        val movies = movieDao.getMoviesByCategory(category)
        return ApiResponse(movies, 0, 0)
    }

    override suspend fun saveMovie(movie: MovieItem) {
        movieDao.insert(movie)
    }
}


