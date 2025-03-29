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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun RouteItem(modifier: Modifier = Modifier) {
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
                Text(text = buildAnnotatedString {
                    append("DEP")
                    append("  ")
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = 0,
                        end = 3
                    )
                    append("Example first airport")
                })
                Text(text = "ARRIVE")
                Text(text = buildAnnotatedString {
                    append("ARR")
                    append("  ")
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = 0,
                        end = 3
                    )
                    append("Example second airport")
                })
            }
            IconButton(
                onClick = {}
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
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
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
        RouteItem()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    MaterialTheme {
        CustomTextField()
    }
}