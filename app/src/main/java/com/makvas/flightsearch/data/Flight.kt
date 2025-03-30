package com.makvas.flightsearch.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    @ColumnInfo(name = "iata_code")
    val iata: String,

    val passengers: Int
)

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "departure_code")
    val departure: String,

    @ColumnInfo(name = "destination_code")
    val destination: String,
)