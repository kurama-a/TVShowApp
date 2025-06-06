package com.eseo.cinemaproject.repository

import com.eseo.cinemaproject.service.TVShowsApiService
import javax.inject.Inject

class TVShowsRepository @Inject constructor(
    private val apiService: TVShowsApiService
) {
    suspend fun getShows(page: Int) = apiService.getMostPopularShows(page)
}