package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.MainActivity
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.LocationService
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ActivityContext
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ApplicationContext
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationRepository
import javax.inject.Singleton

/**
 * Created by Wolf on 04/03/2018.
 */
@Module(includes = [
        MainActivityContextModule::class])
class LocationServiceModule{
    @Provides
    fun getLocationService(@ActivityContext context: Context,fusedLocationProviderClient:FusedLocationProviderClient): LocationRepository{
        return LocationService(context,fusedLocationProviderClient)
    }

    @Provides
    fun fusedLocation(@ActivityContext context: Context): FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(context);
    }

}