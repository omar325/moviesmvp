package com.example.moviesmvp.infrastructure

import com.example.moviesmvp.domain.ApiResponse
import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem

interface Repository {
    suspend fun getMoviesByCategory(category: String): ApiResponse
    suspend fun saveMovie(movie: MovieItem)
}