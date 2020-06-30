package com.example.data

import com.example.data.remote.model.NasaAPODModel
import com.example.domain.model.NasaAPODEntity
import java.util.UUID

object TestsFakeFactory  {
    private val titleString = UUID.randomUUID().toString()
    private val explanationString = UUID.randomUUID().toString()
    private val mediaTypeString = UUID.randomUUID().toString()
    private val mediaUrlString = UUID.randomUUID().toString()
    private val urlString = UUID.randomUUID().toString()

    fun makeFakeNasaAPODModel(): NasaAPODModel {
        return NasaAPODModel(
            title = titleString,
            explanation = explanationString,
            mediaType = mediaTypeString,
            mediaUrl = mediaUrlString,
            url = urlString
        )
    }

    fun makeFakeNasaAPODEntity(): NasaAPODEntity {
        return NasaAPODEntity(
            title = titleString,
            explanation = explanationString,
            mediaType = mediaTypeString,
            mediaUrl = mediaUrlString,
            url = urlString
        )
    }
}