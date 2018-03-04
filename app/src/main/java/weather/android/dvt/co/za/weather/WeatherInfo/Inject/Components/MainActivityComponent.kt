package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Component
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.LocationServiceModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.MainActivityContextModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ActivityContext
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationRepository


/**
 * Created by Wolf on 03/03/2018.
 */
@Component(modules = [
        MainActivityContextModule::class,
        LocationServiceModule::class], dependencies = arrayOf(WeatherServiceComponent::class))

interface MainActivityComponent {
    //fun inject(@ActivityContext context: Context)
    fun getLocationService(): LocationRepository
}