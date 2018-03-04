package weather.android.dvt.co.za.weather.WeatherInfo.di.Modules

import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherDataRepositoryImpl
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherPresenter
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationDataRepository

/**
 * Created by Wolf on 04/03/2018.
 */
@Module(includes = [WeatherDataRepositoryModel::class, LocationDataRepositoryModel::class])
class WeatherPresenterModule{

    @Provides
    fun weatherPresenter(weatherServiceRepository: WeatherDataRepositoryImpl, locationDataRepository: LocationDataRepository): WeatherPresenter{
        return WeatherPresenter(weatherServiceRepository, locationDataRepository)
    }
}
