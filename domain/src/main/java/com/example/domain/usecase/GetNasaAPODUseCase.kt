package com.example.domain.usecase

import com.example.domain.model.NasaAPODEntity
import com.example.domain.repository.NasaAPODRepository
import javax.inject.Inject

class GetNasaAPODUseCase @Inject constructor(private val nasaAPODRepository: NasaAPODRepository) {

    suspend fun getNasaAstronomyPictureOfDay(): NasaAPODEntity =
        nasaAPODRepository.getNasaPictureOfTheDay()
}
