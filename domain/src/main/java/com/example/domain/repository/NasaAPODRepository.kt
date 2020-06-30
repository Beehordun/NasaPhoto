package com.example.domain.repository

import com.example.domain.model.NasaAPODEntity

interface NasaAPODRepository {
    suspend fun getNasaPictureOfTheDay(): NasaAPODEntity
    suspend fun getNasaPictureOfTheDayForDate(date: String): NasaAPODEntity
}
