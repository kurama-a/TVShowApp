package com.eseo.cinemaproject.service

import com.eseo.cinemaproject.model.TVShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsApiService {

    @GET("most-popular")
    suspend fun getMostPopularShows(
        @Query("page") page: Int = 1
    ): Response<TVShowsResponse>
}
