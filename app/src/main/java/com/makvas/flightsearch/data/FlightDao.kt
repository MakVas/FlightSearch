package com.makvas.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Query(
        "SELECT * FROM airport " +
        "WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%' " +
        "ORDER BY passengers ASC"
    )
    fun getAirport(query: String): Flow<List<Airport>>

    @Query(
        "SELECT * FROM airport " +
        "WHERE id != :query " +
        "ORDER BY passengers ASC"
    )
    fun getDestinations(query: String): Flow<List<Airport>>
}

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}