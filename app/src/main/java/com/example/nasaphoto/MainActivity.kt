package com.example.nasaphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.presentation.util.ViewState
import com.example.presentation.viewmodel.NasaAPODViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val nasaAPODViewModel: NasaAPODViewModel by viewModels()
    private val owner = { lifecycle }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureView()
        nasaAPODViewModel.getNasaAstronomyPictureOfDay()
        observeLiveData()
    }

    private fun configureView() {
        date_picker.setOnDatePickedListener { date ->
            nasaAPODViewModel.getNasaAstronomyPictureOfDayForDate(date)
        }
    }

    private fun observeLiveData() {
       nasaAPODViewModel.nasaAPODLiveData.observe(owner) { viewState ->
           when(viewState) {
               is ViewState.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
               is ViewState.Success -> {
                   Toast.makeText(this, "Success ${viewState.data.title}", Toast.LENGTH_LONG).show()
               }
               is ViewState.Error.NoConnectionError -> {
                   Toast.makeText(this, "Error no connection", Toast.LENGTH_LONG).show()
               }

               is ViewState.Error.ServerError -> {
                   Toast.makeText(this, "Error no server error", Toast.LENGTH_LONG).show()
               }
           }

       }
    }
}