package weather.android.dvt.co.za.weather.WeatherInfo.di

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import weather.android.dvt.co.za.weather.MainActivity
import weather.android.dvt.co.za.weather.WeatherInfo.di.Components.WeatherComponent

/**
 * Created by Wolf on 03/03/2018.
 */

val AppCompatActivity.weatherComponent: WeatherComponent
    get() = this.weatherComponent

val Fragment.weatherComponent: WeatherComponent
    get() = (activity as MainActivity).weatherComponent

