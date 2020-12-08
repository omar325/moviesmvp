package com.example.moviesmvp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
@Entity(tableName = "movie_table")
data class MovieItem(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id") val id: Int,
    @ColumnInfo(name = "title")
    @field:SerializedName("title") val title: String,
    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average") val voteAverage: Float,
    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path") val posterPath: String?,
    @ColumnInfo(name = "category")
    @field:SerializedName("category") var category: String?
) {
    fun isSameAs(newItem: MovieItem): Boolean {
        return id == newItem.id
    }

    fun hasSameContentAs(newItem: MovieItem): Boolean {
        return title == newItem.title
                && releaseDate == newItem.releaseDate
    }
}