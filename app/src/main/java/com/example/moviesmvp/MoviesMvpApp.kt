package com.example.moviesmvp

import android.app.Application
import com.example.moviesmvp.infrastructure.room.MovieRoomRepository
import kotlinx.coroutines.GlobalScope

class MoviesMvpApp: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieRoomRepository.getDatabase(this, GlobalScope)
    }
}