package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.RetrofitWeatherDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherPresenter
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationRepository

/**
 * Created by Wolf on 04/03/2018.
 */
@Module(includes = [RetrofitWeatherServiceModule::class,LocationServiceModule::class])
class WeatherPresenterModule{

    @Provides
    fun weatherPresenter(retrofitWeatherService: RetrofitWeatherDataRepository,locationRepository: LocationRepository): WeatherPresenter{
        return WeatherPresenter(retrofitWeatherService,locationRepository)
    }
}
