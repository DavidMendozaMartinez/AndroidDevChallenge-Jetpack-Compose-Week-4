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
package com.example.androiddevchallenge.ui.daily

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Forecast
import com.example.androiddevchallenge.model.ForecastsRepository
import com.example.androiddevchallenge.ui.common.Background
import com.google.accompanist.insets.statusBarsPadding
import java.util.Locale

@Composable
fun Daily() {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ForecastList(
                forecasts = ForecastsRepository.getForecasts()
            )
        }
    }
}

@Composable
fun ForecastList(
    forecasts: List<Forecast>,
    modifier: Modifier = Modifier
) {
    var expandedPosition by remember { mutableStateOf(0) }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(forecasts) { forecast ->
            ForecastItem(
                forecast = forecast,
                expanded = forecasts.indexOf(forecast) == expandedPosition,
                onClick = { expandedPosition = forecasts.indexOf(forecast) }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ForecastItem(
    forecast: Forecast,
    expanded: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            VisibleForecastData(
                forecast = forecast,
                modifier = Modifier.padding(16.dp)
            )
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Box(contentAlignment = Alignment.BottomCenter) {
                    Background(backgroundId = R.drawable.card_bg)
                    HourlyForecast()
                }
            }
        }
    }
}

@Composable
fun VisibleForecastData(
    forecast: Forecast,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Date(
            dayOfWeek = forecast.dayOfWeek,
            dayOfMonth = forecast.dayOfMonth,
            month = forecast.month
        )
        Spacer(modifier = Modifier.weight(1f))
        MaxMinTemperatures(
            max = forecast.temperature.max,
            min = forecast.temperature.min,
            modifier = Modifier.padding(end = 32.dp)
        )
        Weather(
            iconResId = forecast.weather.iconResId,
            labelResId = forecast.weather.stringResId
        )
    }
}

@Composable
fun Date(
    dayOfWeek: String,
    dayOfMonth: String,
    month: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = dayOfWeek,
            style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = dayOfMonth,
            style = MaterialTheme.typography.h4
        )
        Text(
            text = month,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun MaxMinTemperatures(
    max: Float,
    min: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Temperature(
            value = max,
            style = MaterialTheme.typography.h5,
            labelResId = R.string.label_max_temperature
        )
        Temperature(
            value = min,
            style = MaterialTheme.typography.body1,
            labelResId = R.string.label_min_temperature
        )
    }
}

@Composable
fun Temperature(
    value: Float,
    style: TextStyle,
    labelResId: Int,
    modifier: Modifier = Modifier
) {
    val labelStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
        color = MaterialTheme.colors.primary
    )
    val text = buildAnnotatedString {
        withStyle(labelStyle) { append(stringResource(labelResId).toUpperCase(Locale.getDefault())) }
        append("${value}º")
    }

    Text(
        text = text,
        style = style,
        modifier = modifier
    )
}

@Composable
fun Weather(
    iconResId: Int,
    labelResId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = labelResId),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun HourlyForecast(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.label_hourly_forecast),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Image(
            painter = painterResource(id = R.drawable.ic_hourly_forecast),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 16.dp)
        )
    }
}
