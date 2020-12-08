package com.example.moviesmvp.ui.movie_list

import com.example.moviesmvp.domain.MovieCategory
import kotlinx.coroutines.CoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.lang.Exception

class MovieListPresenterTest {

    @Mock
    lateinit var view: MovieListView

    @Mock
    lateinit var interactor: MovieListInteractor

    @Mock
    lateinit var scope: CoroutineScope

    var presenter: MovieListPresenter? = null

    @Rule @JvmField val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        presenter = MovieListPresenter(view, interactor, scope)
    }

    @After
    fun tearDown() {
        presenter = null
    }

    @Test
    fun fetch_movies_shows_progress_bar() {
        presenter!!.fetchMoviesByCategory(MovieCategory.LATEST)
        then(view).should().showProgressBar(true)
    }

    @Test
    fun refresh_movies_hides_progress_bar() {
        presenter!!.refresh(MovieCategory.LATEST)
        then(view).should().showProgressBar(false)
    }

    @Test
    fun fetch_success_hides_progress_bar() {
        presenter!!.onMovieListSuccess(emptyList())
        then(view).should().showProgressBar(false)
    }

    @Test
    fun fetch_error_hides_progress_bar() {
        presenter!!.onMovieListError(Exception(""))
        then(view).should().showProgressBar(false)
    }

    @Test
    fun fetch_success_updates_list() {
        presenter!!.onMovieListSuccess(emptyList())
        then(view).should().updateList(emptyList())
    }

    @Test
    fun fetch_error_shows_error() {
        presenter!!.onMovieListError(Exception(""))
        then(view).should().showErrorMessage("An error occurred while fetching the data")
    }
}