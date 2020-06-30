package com.example.presentation

import com.example.core.dispatcher.AppCoroutineDispatchers
import com.example.domain.model.NasaAPODEntity
import com.example.presentation.model.NasaAPOD
import kotlinx.coroutines.Dispatchers
import java.util.UUID

object TestFactory {

    private val titleString = UUID.randomUUID().toString()
    private val explanationString = UUID.randomUUID().toString()
    private val mediaTypeString = UUID.randomUUID().toString()
    private val mediaUrlString = UUID.randomUUID().toString()
    private val urlString = UUID.randomUUID().toString()

    fun makeNasaAPOD(): NasaAPOD {
        return NasaAPOD(
            explanation = explanationString,
            title = titleString,
            url = urlString,
            mediaType = mediaTypeString,
            mediaUrl = mediaUrlString
        )
    }

    fun makeNasaAPODEntity(): NasaAPODEntity {
        return NasaAPODEntity(
            explanation = explanationString,
            title = titleString,
            url = urlString,
            mediaType = mediaTypeString,
            mediaUrl = mediaUrlString
        )
    }

    fun getAppDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default
    )
}