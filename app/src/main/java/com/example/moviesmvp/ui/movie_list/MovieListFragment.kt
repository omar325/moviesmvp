package com.example.moviesmvp.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesmvp.R
import com.example.moviesmvp.domain.MovieCategory
import com.example.moviesmvp.domain.MovieItem
import com.example.moviesmvp.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment(
    private val category : MovieCategory,
    private val movieListInteractor: MovieListInteractor
) : Fragment(), MovieListView {

    companion object {
        val TAG = "MovieListFragment"
    }

    private val adapter by lazy {
        MovieListAdapter { selectedMovie ->
            goToMovieDetail(selectedMovie)
        }
    }

    private val movieListPresenter by lazy {
        MovieListPresenter(
            this,
            movieListInteractor,
            viewLifecycleOwner.lifecycleScope
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieListPresenter.fetchMoviesByCategory(category)

        movie_list.layoutManager = LinearLayoutManager(context)
        movie_list.adapter = adapter

        swipe_layout.setOnRefreshListener {
            movieListPresenter.refresh(category)
        }
    }

    override fun onResume() {
        super.onResume()
        swipe_layout.isRefreshing = false
        if(adapter.itemCount > 0 && loading.visibility == VISIBLE) {
            loading.visibility = GONE
            swipe_layout.isEnabled = true
        }
    }

    private fun goToMovieDetail(selectedMovie: MovieItem) {
        activity?.let {
            if(it !is MainActivity) return
            it.goToMovieDetailFragment(selectedMovie)
        }
    }

    override fun showProgressBar(show: Boolean) {
        loading.visibility = if(show) VISIBLE else GONE
        //swipe_layout.isEnabled = !show
    }

    override fun updateList(movies: List<MovieItem>) {
        adapter.submitList(movies)
        swipe_layout.isEnabled = true
        swipe_layout.isRefreshing = false
    }

    override fun showErrorMessage(message: String) {
        with(Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)) {
            setAction("RETRY") {
                movieListPresenter.refresh(category)
                swipe_layout.isRefreshing = true
            }
            show()
        }
        swipe_layout.isEnabled = true
        swipe_layout.isRefreshing = false
    }
}