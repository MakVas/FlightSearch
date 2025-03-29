package com.makvas.flightsearch.ui.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makvas.flightsearch.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

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
                scrollState = scrollState
            )
        }
    ) {
        FavoriteRoutes(
            modifier = Modifier,
            contentPadding = it
        )
    }
}

@Composable
private fun FavoriteRoutes(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    scrollState: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
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
        FavoriteRoutes(
            contentPadding = PaddingValues(
                top = 0.dp,
                bottom = 0.dp,
                start = 0.dp,
                end = 0.dp
            )
        )
    }
}
