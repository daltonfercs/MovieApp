package com.daltonfercs.movieapp.data.remote

import com.daltonfercs.movieapp.application.AppConstants
import com.daltonfercs.movieapp.data.model.MovieList
import com.daltonfercs.movieapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)


    suspend fun getTopRateMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)


    suspend fun getPopularMovie(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}