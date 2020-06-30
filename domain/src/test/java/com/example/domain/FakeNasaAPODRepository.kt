package com.example.domain

import com.example.domain.model.NasaAPODEntity
import com.example.domain.repository.NasaAPODRepository
import java.util.UUID

class FakeNasaAPODRepository : NasaAPODRepository {

    private val titleString = UUID.randomUUID().toString()
    private val explanationString = UUID.randomUUID().toString()
    private val mediaTypeString = UUID.randomUUID().toString()
    private val mediaUrlString = UUID.randomUUID().toString()
    private val urlString = UUID.randomUUID().toString()

    val nasaAPODEntity by lazy {
        NasaAPODEntity(
            title = titleString,
            explanation = explanationString,
            mediaType = mediaTypeString,
            mediaUrl = mediaUrlString,
            url = urlString
        )
    }

    override suspend fun getNasaPictureOfTheDay(): NasaAPODEntity {
       return nasaAPODEntity
    }

    override suspend fun getNasaPictureOfTheDayForDate(date: String): NasaAPODEntity {
        return nasaAPODEntity
    }
}
