package com.example.domain.usecase

import com.example.domain.model.NasaAPODEntity
import com.example.domain.repository.NasaAPODRepository
import javax.inject.Inject

class GetNasaAPODForDateUseCase @Inject constructor(private val nasaAPODRepository: NasaAPODRepository) {

    suspend fun getNasaAstronomyPictureOfDayForDate(date: String): NasaAPODEntity =
        nasaAPODRepository.getNasaPictureOfTheDayForDate(date)
}
