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
package com.example.androiddevchallenge.model

import androidx.compose.runtime.Immutable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Immutable
data class Forecast(
    val date: Calendar,
    val temperature: Temperature,
    val weather: Weather
) {
    var dayOfWeek: String = with(Locale.getDefault()) {
        SimpleDateFormat("EEE", this).format(date.time).toUpperCase(this)
    }
    var dayOfMonth: String = SimpleDateFormat("dd", Locale.getDefault()).format(date.time)

    var month: String = with(Locale.getDefault()) {
        SimpleDateFormat("MMM", this).format(date.time).toUpperCase(this)
    }
}

object ForecastsRepository {
    fun getForecasts() = forecasts
}

val forecasts = listOf(
    Forecast(
        date = Calendar.getInstance(),
        temperature = Temperature(
            min = 68.1F,
            max = 77.9F
        ),
        weather = Weather.CLEAR
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) },
        temperature = Temperature(
            min = 65.3F,
            max = 73.3F
        ),
        weather = Weather.WIND
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 2) },
        temperature = Temperature(
            min = 55.4F,
            max = 62.9F
        ),
        weather = Weather.CLOUDS
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 3) },
        temperature = Temperature(
            min = 58.3F,
            max = 63.3F
        ),
        weather = Weather.RAIN
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 4) },
        temperature = Temperature(
            min = 55.5F,
            max = 59.3F
        ),
        weather = Weather.STORM
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 5) },
        temperature = Temperature(
            min = 50.1F,
            max = 53.7F
        ),
        weather = Weather.SNOW
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 6) },
        temperature = Temperature(
            min = 31.8F,
            max = 43.8F
        ),
        weather = Weather.RAIN
    )
)
