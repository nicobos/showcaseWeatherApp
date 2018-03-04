package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.location.Location
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherData
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherRanges
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import javax.inject.Inject


/**
 * Created by Wolf on 03/03/2018.
 */
class RetrofitWeatherDataRepository @Inject constructor(
        private val weatherService: IRetrofitWeatherService): WeatherDataRepository {

    val API_KEY = "7b8dd00d3fb67c66a94995abf0e58603"
    var disposableList: CompositeDisposable = CompositeDisposable()

    override fun getWeatherInfoRetrofit(location: Location?, iWeatherDataCallback: WeatherDataRepository.IWeatherDataCallback) {

        val returnData = WeatherModel()

        val weatherObservable = weatherService?.getWeatherInfoRetrofit(location!!.latitude.toString(),location.longitude.toString(),API_KEY)!!
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
                    returnData.setminTempCelcius(weatherData?.main?.temp_min!!)
                    returnData.setLocationAdres(weatherData?.name!!,weatherData?.sys?.country!!)
                    returnData.imageResourceId = WeatherRanges.getImageResource(weatherData?.weather?.getOrNull(0)?.id)
                    iWeatherDataCallback.onSuccess(returnData)
                }
                /* Clear all threads that are active. If any */
                disposableList.clear()

            }

            override fun onError(e: Throwable) {
                Log.e(RetrofitWeatherDataRepository::class.java.simpleName,e.message)
                iWeatherDataCallback.onFailure(e.message!!)
            }
        })

    }




}