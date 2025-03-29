package com.makvas.flightsearch

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.makvas.flightsearch.ui.screen.FlightSearchContent

@Composable
fun FlightSearchApp(){
    Scaffold(
        topBar = { FlightSearchTopAppBar() }
    ) {  contentPadding ->
        FlightSearchContent(
            contentPadding = contentPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchTopAppBar(){
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}