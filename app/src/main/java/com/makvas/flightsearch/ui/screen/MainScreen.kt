package com.makvas.flightsearch.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makvas.flightsearch.R
import com.makvas.flightsearch.data.Airport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .nestedScroll(scrollState.nestedScrollConnection)
            .padding(contentPadding),
        topBar = {
            SearchBar(
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_small),
                    start = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small)
                ),
                scrollState = scrollState,
                searchQuery = uiState.query,
                onSearchQueryChange = {
                    viewModel.updateSearchQuery(it)
                    if(it.isNotBlank()) {
                        viewModel.updateCurrentScreen(Screen.AIRPORTS)
                        viewModel.getAirports(it)
                    }else{
                        viewModel.updateCurrentScreen(Screen.FAVORITES)
                    }
                }
            )
        }
    ) {
        when(uiState.currentScreen) {
            Screen.AIRPORTS -> SearchSuggestions(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_medium))
                    .fillMaxSize(),
                contentPadding = it,
                airports = uiState.airports,
            )

            Screen.DESTINATIONS -> SearchResults(
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_medium))
                    .fillMaxSize(),
                contentPadding = it
            )

            Screen.FAVORITES -> SearchResults(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = it
            )
        }
    }
}

@Composable
private fun SearchResults(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .padding(horizontal = dimensionResource(R.dimen.padding_medium))
            .fillMaxSize(),
    ) {
        item {
            Text(
                text = "Favorite routes",
                modifier = Modifier
                    .padding(vertical = dimensionResource(R.dimen.padding_small))
            )
        }
        items(5) {
            RouteItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun SearchSuggestions(
    modifier: Modifier,
    airports: List<Airport>,
    onClick: (Airport) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize(),
    ) {
        items(airports.size) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        //TODO: Handle click
                    }
                    .padding(vertical = dimensionResource(R.dimen.padding_small))
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                text = buildAnnotatedString {
                    append(airports[it].iata)
                    append("  ")
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = 0,
                        end = 3
                    )
                    append(airports[it].name)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    scrollState: TopAppBarScrollBehavior,
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        windowInsets = TopAppBarDefaults.windowInsets.exclude(WindowInsets.statusBars),
        scrollBehavior = scrollState,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
        ),
        title = {
            CustomTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun FlightSearchContentPreview() {
    MaterialTheme {
        SearchResults(
            contentPadding = PaddingValues(
                top = 0.dp,
                bottom = 0.dp,
                start = 0.dp,
                end = 0.dp
            )
        )
    }
}
