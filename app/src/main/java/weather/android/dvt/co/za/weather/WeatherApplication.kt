package weather.android.dvt.co.za.weather

import android.app.Application
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.DaggerWeatherServiceComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.WeatherServiceComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.AppContextModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.MainActivityContextModule


/**
 * Created by Wolf on 03/03/2018.
 */
class WeatherApplication : Application() {

    //lateinit var component: WeatherServiceComponent

    override fun onCreate() {
        super.onCreate()

       /* component = DaggerWeatherServiceComponent.builder()
                .appContextModule(AppContextModule(this))
                .build()*/

    }


}