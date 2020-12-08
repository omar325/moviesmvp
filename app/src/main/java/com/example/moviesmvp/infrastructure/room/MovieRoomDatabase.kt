package com.example.moviesmvp.infrastructure.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Database(entities = arrayOf(MovieItem::class), version = 1)
abstract class MovieRoomRepository: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomRepository? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MovieRoomRepository {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomRepository::class.java,
                        "movie_database"
                    ).addCallback(
                        MovieDatabaseCallback(
                            scope
                        )
                    )
                        .build()

                    INSTANCE = instance
                    return instance
                }
        }
    }

    private class MovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch {
                    //populateDatabase(database.movieDao())
                }
            }
        }

        fun populateDatabase(movieDao: MovieDao) {
            movieDao.deleteAll()
            with(movieDao) {
                insert(MovieItem(0, "Movie 0", "Release Date", 3.5f, "", MovieCategory.UPCOMING.name))
                insert(MovieItem(1, "Movie 1", "Release Date", 3.5f, "", MovieCategory.LATEST.name))
                insert(MovieItem(2, "Movie 2", "Release Date", 3.5f, "", MovieCategory.POPULAR.name))
            }
        }
    }
}