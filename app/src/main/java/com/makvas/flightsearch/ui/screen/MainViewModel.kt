package com.makvas.flightsearch.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.makvas.flightsearch.FlightSearchApplication
import com.makvas.flightsearch.data.AirportRepository
import com.makvas.flightsearch.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun updateCurrentScreen(screen: Screen) {
        _uiState.update {
            it.copy(
                currentScreen = screen
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(
                query = query
            )
        }
    }

    fun getAirports(query: String) {
        viewModelScope.launch {
                val airportsL = airportRepository.getAirport(query)
                _uiState.update { it.copy(airports = airportsL.first()) }
                //_uiState.update { it.copy(airports = emptyList()) }
        }
    }

    fun getDestinations(query: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    airports = airportRepository.getDestinations(query).first(),
                    currentScreen = Screen.DESTINATIONS,
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                val airportRepository = application.container.airportRepository
                val favoriteRepository = application.container.favoriteRepository
                MainViewModel(
                    airportRepository = airportRepository,
                    favoriteRepository = favoriteRepository
                )
            }
        }
    }
}