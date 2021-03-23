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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Forecast
import com.example.androiddevchallenge.model.ForecastsRepository
import com.example.androiddevchallenge.ui.common.Background
import com.google.accompanist.insets.statusBarsPadding

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
            ForecastList(forecasts = ForecastsRepository.getForecasts())
        }
    }
}

@Composable
fun ForecastList(forecasts: List<Forecast>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(forecasts) { forecast ->
            ForecastItem(forecast = forecast, forecasts.first() == forecast)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ForecastItem(forecast: Forecast, initiallyExpanded: Boolean = false) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded })
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(
                    expandFrom = Alignment.Top
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                )
            ) {
                Background(backgroundId = R.drawable.card_bg)
            }
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = forecast.dayOfWeek,
                            style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = forecast.dayOfMonth,
                            style = MaterialTheme.typography.h4
                        )
                        Text(
                            text = forecast.month,
                            style = MaterialTheme.typography.caption
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1F)
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = "${forecast.temperature.max}º",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "${forecast.temperature.min}º",
                            style = MaterialTheme.typography.body1
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Image(
                            painter = painterResource(id = forecast.weather.iconResId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(id = forecast.weather.stringResId),
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.label_next_hours),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 80.dp,)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_hourly),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 48.dp, end = 48.dp, top = 16.dp, bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
