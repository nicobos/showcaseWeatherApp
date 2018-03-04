package weather.android.dvt.co.za.weather.WeatherInfo.Repositories

import android.location.Location
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel

/**
 * Created by Wolf on 03/03/2018.
 */
interface WeatherDataRepository {
    fun getWeatherInfoRetrofit(location: Location?,iWeatherDataCallback: IWeatherDataCallback)
    interface IWeatherDataCallback{
        fun onSuccess(weatherModel: WeatherModel);
        fun onFailure(error: String);
    }
}