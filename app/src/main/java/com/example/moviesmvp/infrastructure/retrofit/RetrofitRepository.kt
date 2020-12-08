package com.example.moviesmvp.infrastructure.retrofit

import com.example.moviesmvp.domain.ApiResponse
import com.example.moviesmvp.infrastructure.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitRepository: Repository {

    @GET("movie/{category}?language=en-US&page=undefined&api_key=ecf5db3f477d7289a182b010728ede7b")
    override suspend fun getMoviesByCategory(@Path("category") category: String): ApiResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): RetrofitRepository {
            val logger = HttpLoggingInterceptor().apply { level =
                HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitRepository::class.java)
        }
    }
}