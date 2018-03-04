package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.RetrofitWeatherDataRepository

/**
 * Created by Wolf on 04/03/2018.
 */
@Module(includes = arrayOf(WeatherServiceModule::class))
class RetrofitWeatherServiceModule{
    @Provides
    fun retrofitWeatherService(weatherService: IRetrofitWeatherService): WeatherDataRepository {
        return RetrofitWeatherDataRepository(weatherService)
    }
}