package com.makvas.flightsearch.ui.screen

import com.makvas.flightsearch.data.Airport

enum class Screen {
    AIRPORTS,
    DESTINATIONS,
    FAVORITES
}

data class FlightItem(
    val departure: Airport,
    val destination: Airport
)

data class MainUiState(
    val currentScreen: Screen = Screen.FAVORITES,
    val query: String = "",
    val currentAirport: Airport = Airport(0, "", "", 0),
    val airports: List<Airport> = emptyList(),
    val flights: List<FlightItem> = emptyList(),
    val favorites: List<FlightItem> = emptyList()
)