package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import android.util.Log
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.IWeatherPresenter
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Wolf on 03/03/2018.
 */
@Singleton
class WeatherPresenter @Inject constructor(private val weatherDataRepository: RetrofitWeatherDataRepository,
                                           private val locationRepository: LocationRepository):
        ViewModel(),
        IWeatherPresenter {

     var weatherInfo: MutableLiveData<WeatherModel> = MutableLiveData()

     init{
          Log.d(WeatherPresenter::class.java.simpleName,"Presenter intialized")

     }

     override fun updateWeatherInfo() {
          locationRepository.getLocation(object: LocationRepository.ILocationCallback{
               override fun onSuccess(location: Location) {
                    weatherDataRepository.getWeatherInfoRetrofit(location, object: WeatherDataRepository.IWeatherDataCallback{
                         override fun onSuccess(weatherModel: WeatherModel) {
                              weatherInfo.postValue(weatherModel)
                         }

                         override fun onFailure(error: String) {
                              Log.e(this.javaClass.simpleName, error)
                         }})
               }

               override fun onFailure(error: String) {
                    Log.e(this.javaClass.simpleName,error)
               }
          })
     }


}