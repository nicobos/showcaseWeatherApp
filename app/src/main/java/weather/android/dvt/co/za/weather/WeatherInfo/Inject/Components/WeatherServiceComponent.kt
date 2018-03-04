package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components

import android.content.Context
import dagger.Component
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherPresenter
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.AppContextModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.RetrofitWeatherServiceModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.WeatherPresenterModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.WeatherServiceModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ApplicationContext

/**
 * Created by Wolf on 03/03/2018.
 */
@Component(modules = arrayOf(WeatherServiceModule::class,
        AppContextModule::class,
        WeatherPresenterModule::class,
        RetrofitWeatherServiceModule::class))

interface WeatherServiceComponent {
    //fun inject(mainActivity: MainActivity)
    fun getWeatherPresenter(): WeatherPresenter
}