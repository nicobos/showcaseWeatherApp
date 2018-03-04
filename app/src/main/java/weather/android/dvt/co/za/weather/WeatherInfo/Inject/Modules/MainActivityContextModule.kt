package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import dagger.Provides
import android.app.Activity
import android.content.Context
import dagger.Module
import weather.android.dvt.co.za.weather.MainActivity
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ActivityContext


/**
 * Created by Wolf on 03/03/2018.
 */
@Module
class MainActivityContextModule internal constructor(@ActivityContext context: Context) {

    @field:ActivityContext
    private val context: Context

    init {
        this.context = context
    }

    @Provides
    @ActivityContext
    fun context(): Context {
        return context
    }
}