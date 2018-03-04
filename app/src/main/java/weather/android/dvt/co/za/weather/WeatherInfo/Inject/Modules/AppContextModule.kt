package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ApplicationContext

/**
 * Created by Wolf on 03/03/2018.
 */
@Module
class AppContextModule(@ApplicationContext context: Application) {

    @field:ApplicationContext
    val context: Context

    init {
        this.context = context
    }

    @ApplicationContext
    @Provides
    fun context(): Context{ return context.applicationContext}
}