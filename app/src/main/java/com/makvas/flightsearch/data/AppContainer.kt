package com.makvas.flightsearch.data

import android.content.Context

interface AppContainer {
    val airportRepository: AirportRepository
    val favoriteRepository: FavoriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        AirportRepositoryImpl(FlightDatabase.getDatabase(context).airportDao())
    }

    override val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepositoryImpl(FlightDatabase.getDatabase(context).favoriteDao())
    }
}