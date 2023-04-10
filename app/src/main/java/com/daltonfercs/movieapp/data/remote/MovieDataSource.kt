package com.daltonfercs.movieapp.data.remote


import ccom.daltonfercs.movieapp.domain.WebService
import com.daltonfercs.movieapp.application.AppConstants
import com.daltonfercs.movieapp.data.model.MovieList


class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList {
        return webService.getUpcomingMovies(AppConstants.API_KEY)
    }

    suspend fun getTopRatedMovies(): MovieList {
        return webService.getTopRatedMovies(AppConstants.API_KEY)
    }

    suspend fun getPopularMovies(): MovieList {
        return webService.getPopulardMovies(AppConstants.API_KEY)
    }
}