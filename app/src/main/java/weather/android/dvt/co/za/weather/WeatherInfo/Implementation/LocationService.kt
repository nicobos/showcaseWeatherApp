package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.app.Activity
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import weather.android.dvt.co.za.weather.WeatherInfo.ILocationService


/**
 * Created by Wolf on 03/03/2018.
 */
class LocationService(context: Context?, locationCallback: LocationCallback) : ILocationService {

    var mFusedLocationClient: FusedLocationProviderClient? = null
    var mContext: Context?
    var mLocationCallback: LocationService.LocationCallback

    interface LocationCallback {
        fun locationReceived(location: Location)
    }

    init {
        mContext = context
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext!!);
        mLocationCallback = locationCallback
    }

    /* Get current location from android location provider */
    override fun getCurrentLocation() {
        var mLocation:Location? = null

        @Suppress
        mFusedLocationClient?.getLastLocation()!!
                .addOnSuccessListener(mContext as Activity, OnSuccessListener<Location> { location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        mLocationCallback.locationReceived(location)
                    }
                })
    }
}