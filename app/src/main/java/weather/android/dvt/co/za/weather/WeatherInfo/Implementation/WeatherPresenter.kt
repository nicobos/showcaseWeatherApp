package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import android.support.v4.app.FragmentActivity
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.IWeatherPresenter

/**
 * Created by Wolf on 03/03/2018.
 */
class WeatherPresenter(activity: FragmentActivity?) :
        ViewModel(),
        IWeatherPresenter,
        LocationService.LocationCallback,
        RetrofitWeatherService.weatherCallback{

     var weatherInfo: MutableLiveData<WeatherModel> = MutableLiveData()
     var weatherService: RetrofitWeatherService
     var locationService: LocationService

     init{
          weatherService = RetrofitWeatherService(this)
          locationService = LocationService(activity,this)
     }

     override fun updateWeatherInfo() {
          locationService.getCurrentLocation()
     }

     /* Only once the location is received must the weather be called */
     override fun locationReceived(location: Location) {
         weatherService.getWeatherInfoRetrofit(location)
     }

     /* When the weather data is reveiced from the weather service, update viewmodel to notify views that data has changed */
     override fun weatherDataReceived(weatherModel: WeatherModel) {
          weatherInfo.postValue(weatherModel)
     }
}