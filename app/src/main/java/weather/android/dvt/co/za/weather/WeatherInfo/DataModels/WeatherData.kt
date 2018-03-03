package weather.android.dvt.co.za.weather.WeatherInfo.DataModels

/**
 * Created by Wolf on 03/03/2018.
 */
class WeatherData {
    var coord: Coord? = null
    var weather: List<Weather>? = null
    var base: String? = null
    var main: Main? = null
    var visibility: Int = 0
    var wind: Wind? = null
    var clouds: Clouds? = null
    var dt: Int = 0
    var sys: Sys? = null
    var id: Int = 0
    var name: String? = null
    var cod: Int = 0
}