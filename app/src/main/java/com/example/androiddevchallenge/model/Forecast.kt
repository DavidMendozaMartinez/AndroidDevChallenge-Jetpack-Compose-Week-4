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
    val weather: Weather,
    val chanceOfRain: String,
    val windSpeed: String,
    val humidity: String,
    val pressure: String,
    val uvIndex: String
) {
    var dayOfWeek: String = with(Locale.getDefault()) {
        SimpleDateFormat("EEE", this).format(date.time).toUpperCase(this)
    }
    var dayOfMonth: String = SimpleDateFormat("dd", Locale.getDefault()).format(date.time)

    var month: String = with(Locale.getDefault()) {
        SimpleDateFormat("MMM", this).format(date.time).toUpperCase(this)
    }
}

object ForecastRepository {
    fun getForecasts(@Suppress("UNUSED_PARAMETER") place: Place): List<Forecast> {
        val shuffled = forecasts.shuffled()

        return forecasts.mapIndexed { index, forecast ->
            with(shuffled[index]) {
                forecast.copy(
                    temperature = temperature,
                    weather = weather,
                    chanceOfRain = chanceOfRain,
                    windSpeed = windSpeed,
                    humidity = humidity,
                    pressure = pressure,
                    uvIndex = uvIndex
                )
            }
        }
    }
}

val forecasts = listOf(
    Forecast(
        date = Calendar.getInstance(),
        temperature = Temperature(
            min = 68.1F,
            max = 77.9F,
            realFeel = 75.2F
        ),
        weather = Weather.CLEAR,
        chanceOfRain = "0%",
        windSpeed = "6.2 km/h",
        humidity = "45%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) },
        temperature = Temperature(
            min = 65.3F,
            max = 73.3F,
            realFeel = 71.1F
        ),
        weather = Weather.WIND,
        chanceOfRain = "20%",
        windSpeed = "15.2 km/h",
        humidity = "60%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 2) },
        temperature = Temperature(
            min = 55.4F,
            max = 62.9F,
            realFeel = 61.5F
        ),
        weather = Weather.CLOUDS,
        chanceOfRain = "40%",
        windSpeed = "7.2 km/h",
        humidity = "65%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 3) },
        temperature = Temperature(
            min = 58.3F,
            max = 63.3F,
            realFeel = 60.7F
        ),
        weather = Weather.RAIN,
        chanceOfRain = "85%",
        windSpeed = "5.2 km/h",
        humidity = "87%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 4) },
        temperature = Temperature(
            min = 55.5F,
            max = 59.3F,
            realFeel = 58.2F
        ),
        weather = Weather.STORM,
        chanceOfRain = "72%",
        windSpeed = "9.4 km/h",
        humidity = "81%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 5) },
        temperature = Temperature(
            min = 31.8F,
            max = 43.8F,
            realFeel = 32.2F
        ),
        weather = Weather.SNOW,
        chanceOfRain = "0%",
        windSpeed = "4.2 km/h",
        humidity = "67%",
        pressure = "1023 mbar",
        uvIndex = "0"
    ),
    Forecast(
        date = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 6) },
        temperature = Temperature(
            min = 50.1F,
            max = 53.7F,
            realFeel = 51.6F
        ),
        weather = Weather.RAIN,
        chanceOfRain = "92%",
        windSpeed = "7.3 km/h",
        humidity = "72%",
        pressure = "1023 mbar",
        uvIndex = "0"
    )
)
