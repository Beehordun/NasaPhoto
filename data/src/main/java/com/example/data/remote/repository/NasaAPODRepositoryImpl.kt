package com.example.data.remote.repository

import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.mapper.NasaAPODEntityMapper
import com.example.domain.model.NasaAPODEntity
import com.example.domain.repository.NasaAPODRepository
import javax.inject.Inject

class NasaAPODRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val nasaAPODEntityMapper: NasaAPODEntityMapper

): NasaAPODRepository {

    override suspend fun getNasaPictureOfTheDay(): NasaAPODEntity =
        nasaAPODEntityMapper.mapToNasaAPODEntity(remoteDataSource.getNasaAstronomyOfDayPicture())

    override suspend fun getNasaPictureOfTheDayForDate(date: String): NasaAPODEntity =
        nasaAPODEntityMapper.mapToNasaAPODEntity(
            remoteDataSource.getNasaAstronomyOfDayPictureForDate(date)
        )
}
