package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import timber.log.Timber
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.IWeatherPresenter
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Wolf on 03/03/2018.
 */
@Singleton
class WeatherPresenter @Inject constructor(private val weatherDataRepositoryImpl: WeatherDataRepositoryImpl,
                                           private val locationDataRepository: LocationDataRepository): ViewModel(), IWeatherPresenter {

     var weatherInfo: MutableLiveData<WeatherModel> = MutableLiveData()

     init{
          Timber.d("Presenter intialized")
     }

     override fun updateWeatherInfo() {
          locationDataRepository.getLocation(object: LocationDataRepository.ILocationCallback{
               override fun onSuccess(location: Location) {
                    weatherDataRepositoryImpl.getWeatherInfoRetrofit(location, object: WeatherDataRepository.IWeatherDataCallback{
                         override fun onSuccess(weatherModel: WeatherModel) {
                              Timber.d("Location received successfully")
                              weatherInfo.postValue(weatherModel)
                         }

                         override fun onFailure(error: String) {
                              Timber.e(error)
                         }})
               }

               override fun onFailure(error: String) {
                    Timber.e(error)
               }
          })
     }

}