package com.example.data.mapper

import com.example.data.TestsFakeFactory
import com.example.data.remote.mapper.NasaAPODEntityMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class NasaAPODEntityMapperTest {

    private val nasaAPODModel = TestsFakeFactory.makeFakeNasaAPODModel()
    private val nasaAPODEntityMapper = NasaAPODEntityMapper()

    @Test
    fun mapsToNasaAPODEntity_mapsNasaAPODModel_toNasaAPODEntity() {
        val expectedNasaAPODEntity = TestsFakeFactory.makeFakeNasaAPODEntity()
        val actualNasaAPODEntity = nasaAPODEntityMapper.mapToNasaAPODEntity(nasaAPODModel)

        assertEquals(expectedNasaAPODEntity, actualNasaAPODEntity)
    }
}
