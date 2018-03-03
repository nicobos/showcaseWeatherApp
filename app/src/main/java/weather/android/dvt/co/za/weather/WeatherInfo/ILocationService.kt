package weather.android.dvt.co.za.weather.WeatherInfo

import android.location.Location

/**
 * Created by Wolf on 03/03/2018.
 */
interface ILocationService{

    /* Get the current location of the user */
    fun getCurrentLocation()
}