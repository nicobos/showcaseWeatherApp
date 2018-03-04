package weather.android.dvt.co.za.weather

import android.app.Application
import timber.log.Timber

/**
 * Created by Wolf on 03/03/2018.
 *
 * Application to display weather info
 */
class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            /* todo Implement production crash report tree */
        }
    }


}