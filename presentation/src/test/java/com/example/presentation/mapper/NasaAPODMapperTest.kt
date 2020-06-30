package com.example.presentation.mapper

import com.example.presentation.TestFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class NasaAPODMapperTest {

    @Test
    fun mapToNasaAPOD() {
        val nasaAPODEntity = TestFactory.makeNasaAPODEntity()
        val nasaAPODMapper = NasaAPODMapper()
        val expectedNasaAPOD = TestFactory.makeNasaAPOD()

        val actualNasaAPOD = nasaAPODMapper.mapToNasaAPOD(nasaAPODEntity)

        assertEquals(expectedNasaAPOD, actualNasaAPOD)
    }
}
