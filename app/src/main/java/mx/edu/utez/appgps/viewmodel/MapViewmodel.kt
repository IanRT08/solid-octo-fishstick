package mx.edu.utez.appgps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import mx.edu.utez.appgps.data.db.LocationPoint
import mx.edu.utez.appgps.data.repository.TripRepository

class MapViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TripRepository(application)
    // Simplemente obtenemos TODOS los puntos y los convertimos en un StateFlow
    val allTripPoints: StateFlow<List<LocationPoint>> =
        repository.getAllPoints()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

}