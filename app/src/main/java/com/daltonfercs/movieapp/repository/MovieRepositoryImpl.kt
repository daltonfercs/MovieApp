package com.daltonfercs.movieapp.repository

import com.daltonfercs.movieapp.data.model.MovieList
import com.daltonfercs.movieapp.data.remote.MovieDataSource
import javax.sql.CommonDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRateMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovie()
}