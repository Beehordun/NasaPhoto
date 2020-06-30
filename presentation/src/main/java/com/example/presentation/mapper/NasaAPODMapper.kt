package com.example.presentation.mapper

import com.example.domain.model.NasaAPODEntity
import com.example.presentation.model.NasaAPOD
import javax.inject.Inject

class NasaAPODMapper @Inject constructor() {

    fun mapToNasaAPOD(nasaAPODEntity: NasaAPODEntity): NasaAPOD {
        return NasaAPOD(
            title = nasaAPODEntity.title,
            explanation = nasaAPODEntity.explanation,
            mediaType = nasaAPODEntity.mediaType,
            mediaUrl = nasaAPODEntity.mediaUrl,
            url = nasaAPODEntity.url
        )
    }
}
