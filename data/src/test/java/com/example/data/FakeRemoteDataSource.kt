package com.example.data

import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.model.NasaAPODModel

class FakeRemoteDataSource : RemoteDataSource {

    override suspend fun getNasaAstronomyOfDayPicture(): NasaAPODModel =
        TestsFakeFactory.makeFakeNasaAPODModel()

    override suspend fun getNasaAstronomyOfDayPictureForDate(date: String): NasaAPODModel =
        TestsFakeFactory.makeFakeNasaAPODModel()
}
