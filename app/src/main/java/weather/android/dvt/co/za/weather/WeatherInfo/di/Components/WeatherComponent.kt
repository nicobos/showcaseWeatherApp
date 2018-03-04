package weather.android.dvt.co.za.weather.WeatherInfo.di.Components

import dagger.Component
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherView
import weather.android.dvt.co.za.weather.WeatherInfo.di.Modules.*

/**
 * Created by Wolf on 03/03/2018.
 */
@Component(modules = [WeatherPresenterModule::class, PicassoModule::class,DateModule::class])

interface WeatherComponent {
    fun inject(weatherView: WeatherView)
}