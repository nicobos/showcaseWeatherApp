package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.app.Activity
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ActivityContext
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.MainActivityScope
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationRepository
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Wolf on 03/03/2018.
 */
class LocationService @Inject constructor(
        @ActivityContext private val activityContext: Context,
        private val fusedLocationProviderClient: FusedLocationProviderClient) : LocationRepository {

    override fun getLocation(locationCallback: LocationRepository.ILocationCallback) {
        @Suppress
        fusedLocationProviderClient?.getLastLocation()!!
                .addOnSuccessListener(activityContext as Activity,{ location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        locationCallback.onSuccess(location)
                    }
                    else {
                        locationCallback.onFailure("")
                    }
                })
    }

}