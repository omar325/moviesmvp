package com.example.moviesmvp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.moviesmvp.R
import com.example.moviesmvp.domain.MovieItem
import com.example.moviesmvp.infrastructure.room.MovieRoomRepository
import com.example.moviesmvp.ui.movie_categories.MovieCategoriesFragment
import com.example.moviesmvp.ui.movie_categories.MovieCategoriesPagerAdapter
import com.example.moviesmvp.ui.movie_detail.MovieDetailFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, MovieCategoriesFragment())
            .commit()
    }

    fun goToMovieDetailFragment(movie: MovieItem) {
        navigateToFragment(MovieDetailFragment(movie), MovieDetailFragment.TAG)
    }

    fun navigateToFragment(fragment: Fragment, tag: String, sharedElement: View? = null) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}