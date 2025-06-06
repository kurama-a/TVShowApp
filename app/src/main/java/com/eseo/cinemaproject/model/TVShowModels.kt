package com.eseo.cinemaproject.model

import com.google.gson.annotations.SerializedName

data class TVShowsResponse(
    @SerializedName("total")
    val total: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("tv_shows")
    val tvShows: List<TVShow>
)

data class TVShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("image_thumbnail_path")
    val imageThumbnailPath: String
)