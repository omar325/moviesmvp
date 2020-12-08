package com.example.moviesmvp.domain

import com.example.moviesmvp.domain.MovieItem
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @field:SerializedName("results") val results: List<MovieItem>,
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("total_pages") val totalPages: Int
)