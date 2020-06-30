package com.example.domain.usecases

import com.example.domain.FakeNasaAPODRepository
import com.example.domain.usecase.GetNasaAPODForDateUseCase
import com.example.domain.usecase.GetNasaAPODUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class GetNasaAPODForDateUseCaseTest {

    lateinit var nasaAPODRepository: FakeNasaAPODRepository
    lateinit var getNasaAPODForDateUseCase: GetNasaAPODForDateUseCase

    @Before
    fun setup() {
        nasaAPODRepository = FakeNasaAPODRepository()
        getNasaAPODForDateUseCase = GetNasaAPODForDateUseCase(nasaAPODRepository)
    }

    @Test
    fun getNasaAPODUseCase_shouldCallNasaAPODRepository_andReturnNasaAPODEntity() = runBlocking {
        val expectedNasaAPODEntity = nasaAPODRepository.nasaAPODEntity

        val actualNasaAPODEntity = getNasaAPODForDateUseCase.
        getNasaAstronomyPictureOfDayForDate(UUID.randomUUID().toString())

        Assert.assertEquals(expectedNasaAPODEntity, actualNasaAPODEntity)
    }
}
