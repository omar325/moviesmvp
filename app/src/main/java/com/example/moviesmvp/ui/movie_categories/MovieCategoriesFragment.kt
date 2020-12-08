package com.example.moviesmvp.ui.movie_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.moviesmvp.R
import kotlinx.android.synthetic.main.fragment_movie_categories.*

class MovieCategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie_categories, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            fragment_pager.adapter =
                MovieCategoriesPagerAdapter(
                    it.supportFragmentManager,
                    it.application,
                    it.lifecycleScope
                )
            movie_tabs.setupWithViewPager(fragment_pager)
        }
    }
}