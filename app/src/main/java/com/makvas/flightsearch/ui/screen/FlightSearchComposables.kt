package com.makvas.flightsearch.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.makvas.flightsearch.R
import com.makvas.flightsearch.data.Airport

@Composable
fun RouteItem(
    modifier: Modifier = Modifier,
    departure: Airport,
    destination: Airport,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topEnd = dimensionResource(R.dimen.corner_radius_medium),
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(text = "DEPART")
                Text(
                    text = buildAnnotatedString {
                        append(departure.iata)
                        append("  ")
                        addStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold),
                            start = 0,
                            end = 3
                        )
                        append(departure.name)
                    },
                    maxLines = 1
                )
                Text(text = "ARRIVE")
                Text(
                    text = buildAnnotatedString {
                        append(destination.iata)
                        append("  ")
                        addStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold),
                            start = 0,
                            end = 3
                        )
                        append(destination.name)
                    },
                    maxLines = 1
                )
            }
            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    tint = Color.Gray,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(stringResource(R.string.enter_dep_airp)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
fun RouteItemPreview() {
    MaterialTheme {
        RouteItem(
            departure = Airport(0, "LAX", "Los Angeles International Airport", 1),
            destination = Airport(1, "JFK", "John F. Kennedy International Airport", 1),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    MaterialTheme {
        CustomTextField()
    }
}