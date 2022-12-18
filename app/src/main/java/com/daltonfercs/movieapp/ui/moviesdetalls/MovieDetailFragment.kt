package com.daltonfercs.movieapp.ui.moviesdetalls

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.daltonfercs.movieapp.R
import com.daltonfercs.movieapp.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    lateinit var binding: FragmentMovieDetailBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
    }


}