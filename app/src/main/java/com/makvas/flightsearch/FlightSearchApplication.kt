package com.makvas.flightsearch

import android.app.Application
import com.makvas.flightsearch.data.AppContainer
import com.makvas.flightsearch.data.AppDataContainer

class FlightSearchApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}