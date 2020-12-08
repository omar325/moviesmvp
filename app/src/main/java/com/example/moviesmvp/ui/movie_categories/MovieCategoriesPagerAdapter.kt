package com.example.moviesmvp.ui.movie_categories

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.infrastructure.MovieRepository
import com.example.moviesmvp.infrastructure.retrofit.RetrofitRepository
import com.example.moviesmvp.infrastructure.room.MovieRoomRepository
import com.example.moviesmvp.infrastructure.room.RoomRepository
import com.example.moviesmvp.ui.movie_list.MovieListFragment
import com.example.moviesmvp.ui.movie_list.MovieListInteractor
import kotlinx.coroutines.CoroutineScope

class MovieCategoriesPagerAdapter(
    private val manager: FragmentManager,
    private val application: Application,
    private val scope: CoroutineScope
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val categories = MovieCategory.values()

    override fun getPageTitle(position: Int): CharSequence? =
        categories[position].name

    override fun getItem(position: Int): MovieListFragment =
        MovieListFragment(
            categories[position],
            MovieListInteractor(
                MovieRepository(
                    localRepository = RoomRepository(
                        MovieRoomRepository.getDatabase(application, scope).movieDao()
                    ),
                    remoteRepository = RetrofitRepository.create())
            )
        )

    override fun getCount(): Int =
        categories.size
}