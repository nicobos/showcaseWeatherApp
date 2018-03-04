package weather.android.dvt.co.za.weather.WeatherInfo.Inject

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import weather.android.dvt.co.za.weather.MainActivity
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.MainActivityComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.WeatherServiceComponent

/**
 * Created by Wolf on 03/03/2018.
 */

val AppCompatActivity.mainActivityComponent: MainActivityComponent
    get() = this.mainActivityComponent

val Fragment.mainActivityCompnent: MainActivityComponent
    get() = (activity as MainActivity).mainActivityComponent

val Fragment.component: WeatherServiceComponent
    get() = (activity as MainActivity).component