package weather.android.dvt.co.za.weather.WeatherInfo.Repositories

import android.location.Location
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel

/**
 * Created by Wolf on 04/03/2018.
 */
interface LocationRepository {
    fun getLocation(locationCallback: ILocationCallback)
    interface ILocationCallback{
        fun onSuccess(location: Location);
        fun onFailure(error: String);
    }
}