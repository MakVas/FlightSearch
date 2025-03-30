package com.makvas.flightsearch.data

import kotlinx.coroutines.flow.Flow

class AirportRepositoryImpl(private val airportDao: AirportDao) : AirportRepository {

    override fun getAirport(query: String) =
        airportDao.getAirport(query)

    override fun getDestinations(query: String) =
        airportDao.getDestinations(query)

}

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(favorite: Favorite) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(favorite: Favorite) {
        TODO("Not yet implemented")
    }
}