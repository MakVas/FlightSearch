package com.makvas.flightsearch.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAirport(query: String): Flow<List<Airport>>
    fun getDestinations(query: String): Flow<List<Airport>>
}

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Favorite>>
    suspend fun insert(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
}