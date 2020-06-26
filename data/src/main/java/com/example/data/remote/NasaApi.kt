package com.example.data.remote

import com.example.data.remote.model.NasaAPODModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("/planetary/apod")
    suspend fun getNasaAstronomyOfDayPicture(): NasaAPODModel

    @GET("/planetary/apod")
    suspend fun getNasaAstronomyOfDayPictureForDate(@Query("date") date: String): NasaAPODModel
}
