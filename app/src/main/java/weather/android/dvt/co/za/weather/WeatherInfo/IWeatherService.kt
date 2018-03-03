package weather.android.dvt.co.za.weather.WeatherInfo

import android.location.Location

/**
 * Created by Wolf on 03/03/2018.
 */
interface IWeatherService{
    fun getWeatherInfoRetrofit(location: Location?)
}