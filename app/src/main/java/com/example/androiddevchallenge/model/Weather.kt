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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.androiddevchallenge.R

@Immutable
enum class Weather(@StringRes val stringResId: Int, @DrawableRes val iconResId: Int) {
    CLEAR(R.string.description_clear, R.drawable.ic_clear),
    WIND(R.string.description_wind, R.drawable.ic_wind),
    CLOUDS(R.string.description_clouds, R.drawable.ic_clouds),
    RAIN(R.string.description_rain, R.drawable.ic_rain),
    STORM(R.string.description_storm, R.drawable.ic_storm),
    SNOW(R.string.description_snow, R.drawable.ic_snow)
}
