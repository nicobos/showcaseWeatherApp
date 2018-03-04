package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.content.Context
import android.location.Location
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import weather.android.dvt.co.za.weather.R
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherData
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherRanges
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.di.Scope.ActivityContext
import javax.inject.Inject


/**
 * Created by Wolf on 03/03/2018.
 * Gets weather data from weather sources
 */
class WeatherDataRepositoryImpl @Inject constructor(
        private val weatherService: IRetrofitWeatherService,
        @ActivityContext context: Context): WeatherDataRepository {

    val mContext: Context
    var disposableList: CompositeDisposable = CompositeDisposable()

    init {
        this.mContext = context
    }

    override fun getWeatherInfoRetrofit(location: Location?, iWeatherDataCallback: WeatherDataRepository.IWeatherDataCallback) {

        val returnData = WeatherModel()

        val weatherObservable = weatherService.getWeatherInfoRetrofit(location!!.latitude.toString(), location.longitude.toString(), mContext.getString(R.string.open_weather_api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

        weatherObservable.subscribe(object : Observer<Response<WeatherData>> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                disposableList.add(d)
            }

            override fun onNext(value: Response<WeatherData>) {
                /* Read data if network call was successfull*/
                if (value.code() == 200) {
                    val weatherData = value.body()

                    /* Construct return message to presenter */
                    returnData.setMaxTempCelcius(weatherData?.main?.temp_max!!)
                    returnData.setminTempCelcius(weatherData.main?.temp_min!!)
                    returnData.setLocationAdres(weatherData.name!!, weatherData.sys?.country!!)
                    returnData.imageResourceId = WeatherRanges.getImageResource(weatherData.weather?.getOrNull(0)?.id)
                    returnData.weatherDescription = weatherData.weather?.getOrNull(0)?.main
                    iWeatherDataCallback.onSuccess(returnData)
                }
                /* Clear all threads that are active. If any */
                disposableList.clear()

            }

            override fun onError(e: Throwable) {
                Log.e(WeatherDataRepositoryImpl::class.java.simpleName,e.message)
                iWeatherDataCallback.onFailure(e.message!!)
            }
        })

    }

}