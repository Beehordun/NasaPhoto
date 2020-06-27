package com.example.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.exceptions.NoConnectivityException
import com.example.domain.usecase.GetNasaAPODForDateUseCase
import com.example.domain.usecase.GetNasaAPODUseCase
import com.example.presentation.mapper.NasaAPODMapper
import com.example.presentation.model.NasaAPOD
import com.example.presentation.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NasaAPODViewModel @ViewModelInject constructor(
    private val getNasaAPODUseCase: GetNasaAPODUseCase,
    private val getNasaAPODForDateUseCase: GetNasaAPODForDateUseCase,
    private val nasaAPODMapper: NasaAPODMapper
): ViewModel() {

    private var _nasaAPODLiveData = MutableLiveData<ViewState<NasaAPOD>>()
    var nasaAPODLiveData = _nasaAPODLiveData

    fun getNasaAstronomyPictureOfDay() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                kotlin.runCatching {
                    getNasaAPODUseCase.getNasaAstronomyPictureOfDay()
                }.onSuccess {
                    val nasaAPOD = nasaAPODMapper.mapToNasaAPOD(it)
                    _nasaAPODLiveData.postValue(ViewState.Success(nasaAPOD))
                }.onFailure { exception ->
                    when(exception) {
                        is NoConnectivityException -> {
                            _nasaAPODLiveData.postValue(ViewState.Error.NoConnectionError())
                        }

                        else -> {
                            _nasaAPODLiveData.postValue(ViewState.Error.ServerError())
                        }
                    }
                }
            }
        }
    }

    fun getNasaAstronomyPictureOfDayForDate(date: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                kotlin.runCatching {
                    getNasaAPODForDateUseCase.getNasaAstronomyPictureOfDayForDate(date)
                }.onSuccess {
                    val nasaAPOD = nasaAPODMapper.mapToNasaAPOD(it)
                    _nasaAPODLiveData.postValue(ViewState.Success(nasaAPOD))
                }.onFailure { exception ->
                    when(exception) {
                        is NoConnectivityException -> {
                            _nasaAPODLiveData.postValue(ViewState.Error.NoConnectionError())
                        }

                        else -> {
                            exception.printStackTrace()
                            println("Called -> ${exception.message}")
                            _nasaAPODLiveData.postValue(ViewState.Error.ServerError())
                        }
                    }
               }
            }
        }
    }
}
