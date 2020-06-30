package com.example.data.remote.mapper

import com.example.data.remote.model.NasaAPODModel
import com.example.domain.model.NasaAPODEntity
import javax.inject.Inject

class NasaAPODEntityMapper @Inject constructor() {

    fun mapToNasaAPODEntity(nasaAPODModel: NasaAPODModel): NasaAPODEntity =
        NasaAPODEntity(
            title = nasaAPODModel.title,
            explanation = nasaAPODModel.explanation,
            mediaType = nasaAPODModel.mediaType,
            mediaUrl = nasaAPODModel.mediaUrl ?: "",
            url = nasaAPODModel.url
        )
}
