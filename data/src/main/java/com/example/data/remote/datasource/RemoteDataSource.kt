package com.example.data.remote.datasource

import com.example.data.remote.model.NasaAPODModel

interface RemoteDataSource {
  suspend fun getNasaAstronomyOfDayPicture(): NasaAPODModel
  suspend fun getNasaAstronomyOfDayPictureForDate(date: String): NasaAPODModel
}
