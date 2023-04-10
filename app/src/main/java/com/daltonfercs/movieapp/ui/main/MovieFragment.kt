package com.daltonfercs.movieapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import ccom.daltonfercs.movieapp.domain.RetrofitClient
import ccom.daltonfercs.movieapp.ui.main.adapters.MoviesAdapter
import com.daltonfercs.movieapp.R
import com.daltonfercs.movieapp.core.Resource
import com.daltonfercs.movieapp.data.model.Movie
import com.daltonfercs.movieapp.data.remote.MovieDataSource
import com.daltonfercs.movieapp.databinding.FragmentMovieBinding
import com.daltonfercs.movieapp.domain.MovieRepositoryImpl
import com.daltonfercs.movieapp.presentation.MovieViewModel
import com.daltonfercs.movieapp.presentation.MovieViewModelFactory
import com.daltonfercs.movieapp.ui.main.adapters.concat.PopularConcatAdapter
import com.daltonfercs.movieapp.ui.main.adapters.concat.TopRatedConcatAdapter
import com.daltonfercs.movieapp.ui.main.adapters.concat.UpcomingConcatAdapter



class MovieFragment : Fragment(R.layout.fragment_movie), MoviesAdapter.OnMovieClickListener {

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { MovieViewModelFactory(MovieRepositoryImpl(
        MovieDataSource(RetrofitClient.webservice)
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, UpcomingConcatAdapter(MoviesAdapter(it.data.first.results,this@MovieFragment)))
                        addAdapter(1, TopRatedConcatAdapter(MoviesAdapter(it.data.second.results,this@MovieFragment)))
                        addAdapter(2, PopularConcatAdapter(MoviesAdapter(it.data.third.results,this@MovieFragment)))

                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("FetchError", "Error: $it.exception ")
                    Toast.makeText(requireContext(), "Error: ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie.poster_path,movie.backdrop_path,movie.vote_average.toFloat(),movie.vote_count,movie.overview,movie.title,movie.original_language,movie.release_date)
        findNavController().navigate(action)
    }
}