package com.example.data.remote.datasource

import com.example.data.remote.NasaApi
import com.example.data.remote.model.NasaAPODModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val nasaApi: NasaApi): RemoteDataSource {

    override suspend fun getNasaAstronomyOfDayPicture(): NasaAPODModel =
        nasaApi.getNasaAstronomyOfDayPicture()

    override suspend fun getNasaAstronomyOfDayPictureForDate(date: String): NasaAPODModel =
        nasaApi.getNasaAstronomyOfDayPictureForDate(date)
}
