package com.example.data.repository

import com.example.data.FakeRemoteDataSource
import com.example.data.TestsFakeFactory
import com.example.data.remote.datasource.RemoteDataSource
import com.example.data.remote.mapper.NasaAPODEntityMapper
import com.example.data.remote.repository.NasaAPODRepositoryImpl
import com.example.domain.repository.NasaAPODRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NasaAPODRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var nasaAPODEntityMapper: NasaAPODEntityMapper
    private lateinit var nasaAPODRepository: NasaAPODRepository

    @Before
    fun setUp() {
        remoteDataSource = FakeRemoteDataSource()
        nasaAPODEntityMapper = NasaAPODEntityMapper()
        nasaAPODRepository = NasaAPODRepositoryImpl(remoteDataSource, nasaAPODEntityMapper)
    }

    @Test
    fun getNasaPictureOfTheDay_Returns_NasaAPODEntity() = runBlocking {
        val expectedNasaAPODEntity = TestsFakeFactory.makeFakeNasaAPODEntity()
        val actualNasaAPODEntity = nasaAPODRepository.getNasaPictureOfTheDay()

        assertEquals(expectedNasaAPODEntity, actualNasaAPODEntity)
    }

    @Test
    fun getNasaPictureOfTheDayForDate_Returns_NasaAPODEntity() = runBlocking {
        val expectedNasaAPODEntity = TestsFakeFactory.makeFakeNasaAPODEntity()
        val actualNasaAPODEntity = nasaAPODRepository.getNasaPictureOfTheDayForDate("2002-11-12")

        assertEquals(expectedNasaAPODEntity, actualNasaAPODEntity)
    }
}
