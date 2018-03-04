package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.AppContextModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.ApplicationContext

/**
 * Created by Wolf on 03/03/2018.
 */
@Module(includes = arrayOf(AppContextModule::class))
class PicassoModule {

    @Provides
    fun picasso(@ApplicationContext context: Context): Picasso{
        return Picasso.Builder(context).build()
    }
}