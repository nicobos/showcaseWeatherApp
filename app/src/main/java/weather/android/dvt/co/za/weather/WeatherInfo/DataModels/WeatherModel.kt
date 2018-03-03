package weather.android.dvt.co.za.weather.WeatherInfo.DataModels

import java.util.*

/**
 * Created by Wolf on 03/03/2018.
 *
 * Weather model to handle weather data
 */
class WeatherModel {
    var locationAdres: String? = null
    var minTemp: String? = null
    var maxTemp: String? = null
    var imageResourceId: Int? = null

    /* Set the minimum temperature in Celsius (Convert from fahrenheit) */
    fun setminTempCelcius(valFar: Double){
        this.minTemp = kelvinToCelcius(valFar).toString()
    }

    /* Set the maximum temperature in Celsius (Convert from fahrenheit) */
    fun setMaxTempCelcius(valFar: Double){
        this.maxTemp = kelvinToCelcius(valFar).toString()
    }

    fun setLocationAdres(name:String,countryCode: String){
        locationAdres = StringBuilder().append(name).append(", ").append(getCountryNameFromCode(countryCode)).toString()
    }

    /* convert values from fahrenheit to Celsius */
    fun kelvinToCelcius(kelvinValue: Double): Double{
        return kelvinValue - 273.15
    }

    /* Get country name from country code */
    fun getCountryNameFromCode(countryCode: String): String{
        val loc = Locale("",countryCode)
        return loc.getDisplayCountry()
    }
}