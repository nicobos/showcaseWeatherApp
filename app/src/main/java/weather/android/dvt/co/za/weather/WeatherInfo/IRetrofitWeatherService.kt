package weather.android.dvt.co.za.weather.WeatherInfo

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherData

/**
 * Created by Wolf on 03/03/2018.
 *
 * Service that gets the weather data from openweather.org
 */
interface IRetrofitWeatherService {

    @GET("weather")
    fun getWeatherInfoRetrofit(@Query("lat") lat: String,
                               @Query("lon") lon: String,
                               @Query("appid") appid: String): Observable<Response<WeatherData>>

}