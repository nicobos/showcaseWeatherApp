package weather.android.dvt.co.za.weather.WeatherInfo.di.Modules

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.LocationDataDataRepositoryImpl
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.di.Scope.ActivityContext

/**
 * Created by Wolf on 04/03/2018.
 * Provides the location service
 */
@Module(includes = [MainActivityContextModule::class])
class LocationDataRepositoryModel {

    @Provides
    fun getLocationService(@ActivityContext context: Context,fusedLocationProviderClient:FusedLocationProviderClient): LocationDataRepository {
        return LocationDataDataRepositoryImpl(context,fusedLocationProviderClient)
    }

    @Provides
    fun fusedLocation(@ActivityContext context: Context): FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(context)
    }

}