package com.example.moviesmvp.infrastructure.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesmvp.domain.MovieItem

@Dao
interface MovieDao {
    @Query("SELECT * from movie_table WHERE category = :category")
    suspend fun getMoviesByCategory(category: String): List<MovieItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieItem)

    @Query("DELETE FROM movie_table")
    fun deleteAll()
}