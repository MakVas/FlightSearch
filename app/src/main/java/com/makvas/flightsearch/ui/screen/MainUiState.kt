package com.makvas.flightsearch.ui.screen

import com.makvas.flightsearch.data.Airport
import com.makvas.flightsearch.data.Favorite

enum class Screen {
    AIRPORTS,
    DESTINATIONS,
    FAVORITES
}



data class MainUiState(
    val currentScreen: Screen = Screen.FAVORITES,
    val query: String = "",
    val airports: List<Airport> = emptyList(),
    val destinations: List<Airport> = emptyList(),
    val favorites: List<Favorite> = emptyList()
)