package com.daltonfercs.movieapp.domain

import com.daltonfercs.movieapp.data.model.MovieList


interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}