/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.place

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Place

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlaceChooser(
    places: List<Place>,
    onChosenPlace: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedPosition by remember { mutableStateOf(0) }
    var isPlaceListExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        SelectedPlace(
            place = places[selectedPosition],
            onClick = { isPlaceListExpanded = !isPlaceListExpanded }
        )

        AnimatedVisibility(
            visible = isPlaceListExpanded,
            enter = expandVertically(expandFrom = Alignment.Top),
            exit = shrinkVertically(shrinkTowards = Alignment.Top),
        ) {
            PlaceList(
                places = places,
                selectedPosition = selectedPosition,
                onItemClick = { position ->
                    selectedPosition = position
                    onChosenPlace(places[position])
                    isPlaceListExpanded = false
                }
            )
        }
    }
}

@Composable
fun SelectedPlace(
    place: Place,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colors.background,
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Place,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = place.displayName,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun PlaceList(
    places: List<Place>,
    selectedPosition: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = modifier
        ) {
            items(places) { place ->
                PlaceItem(
                    place = place,
                    selected = places.indexOf(place) == selectedPosition,
                    onClick = { onItemClick(places.indexOf(place)) }
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.places_bg),
            contentDescription = null,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun PlaceItem(
    place: Place,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) MaterialTheme.colors.surface.copy(alpha = 0.4F) else Color.Transparent,
        shape = MaterialTheme.shapes.medium,
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colors.surface) else null
    ) {
        Text(
            text = place.displayName,
            textAlign = TextAlign.Center,
            color = if (selected) MaterialTheme.colors.onSurface else MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
        )
    }
}
