package com.example.domain.usecases

import com.example.domain.FakeNasaAPODRepository
import com.example.domain.usecase.GetNasaAPODUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetNasaAPODUseCaseTest {

    lateinit var nasaAPODRepository: FakeNasaAPODRepository
    lateinit var getNasaAPODUseCase: GetNasaAPODUseCase

    @Before
    fun setup() {
        nasaAPODRepository = FakeNasaAPODRepository()
        getNasaAPODUseCase = GetNasaAPODUseCase(nasaAPODRepository)
    }

    @Test
    fun getNasaAPODUseCase_shouldCallNasaAPODRepository_andReturnNasaAPODEntity() = runBlocking {
        val expectedNasaAPODEntity = nasaAPODRepository.nasaAPODEntity

        val actualNasaAPODEntity = getNasaAPODUseCase.getNasaAstronomyPictureOfDay()

        assertEquals(expectedNasaAPODEntity, actualNasaAPODEntity)
    }
}
