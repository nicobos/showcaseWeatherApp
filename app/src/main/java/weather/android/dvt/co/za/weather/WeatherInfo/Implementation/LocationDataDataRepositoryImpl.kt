package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.app.Activity
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import weather.android.dvt.co.za.weather.WeatherInfo.di.Scope.ActivityContext
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.LocationDataRepository
import javax.inject.Inject


/**
 * Created by Wolf on 03/03/2018.
 * Gets the location from the device
 */
class LocationDataDataRepositoryImpl @Inject constructor(
        @ActivityContext private val activityContext: Context,
        private val fusedLocationProviderClient: FusedLocationProviderClient) : LocationDataRepository {

    override fun getLocation(locationCallback: LocationDataRepository.ILocationCallback) {
        @Suppress
        fusedLocationProviderClient.lastLocation!!
                .addOnSuccessListener(activityContext as Activity,{ location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        locationCallback.onSuccess(location)
                    }
                    else {
                        locationCallback.onFailure("Error: Location is null.")
                    }
                })
    }

}