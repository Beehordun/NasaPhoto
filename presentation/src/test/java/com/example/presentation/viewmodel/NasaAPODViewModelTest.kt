package com.example.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.domain.usecase.GetNasaAPODForDateUseCase
import com.example.domain.usecase.GetNasaAPODUseCase
import com.example.presentation.MainCoroutineRule
import com.example.presentation.TestFactory
import com.example.presentation.mapper.NasaAPODMapper
import com.example.presentation.model.NasaAPOD
import com.example.presentation.runBlockingTest
import com.example.presentation.util.ViewState
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NasaAPODViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getNasaAPODUseCase: GetNasaAPODUseCase
    @Mock
    lateinit var getNasaAPODForDateUseCase: GetNasaAPODForDateUseCase
    @Mock
    lateinit var nasaAPODMapper: NasaAPODMapper
    @Mock
    private lateinit var nasaAPODObserver: Observer<ViewState<NasaAPOD>>

    private val testDispatcher = TestFactory.getAppDispatchers()
    lateinit var nasaAPODViewModel: NasaAPODViewModel
    lateinit var nasaAPODLiveData: MutableLiveData<ViewState<NasaAPOD>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        nasaAPODViewModel =
            NasaAPODViewModel(getNasaAPODUseCase, getNasaAPODForDateUseCase, nasaAPODMapper, testDispatcher)
        nasaAPODLiveData = nasaAPODViewModel.nasaAPODLiveData
    }

    @Test
    fun getNasaAstronomyPictureOfDay_classGetNasaAPODUseCase() = mainCoroutineRule.runBlockingTest {
        nasaAPODViewModel.getNasaAstronomyPictureOfDay()
        verify(getNasaAPODUseCase, times(1))
    }

    @Test
    fun testme() {
        val ade = "https://www.youtube.com/embed/Cd5a5KdPxQc?rel=0"
        println(getVideoIdFromVideoUrl(ade))
    }

    private fun getVideoIdFromVideoUrl(videoUrl: String): String {
        val slashIndex = videoUrl.lastIndexOf('/')
        val amperstandIndex = videoUrl.indexOf('?')
        println(slashIndex)
        println(amperstandIndex)
        return videoUrl.substring(slashIndex + 1, amperstandIndex)
    }
}
