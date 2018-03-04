package weather.android.dvt.co.za.weather.WeatherInfo.di.Modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import weather.android.dvt.co.za.weather.WeatherInfo.di.Scope.ActivityContext

/**
 * Created by Wolf on 03/03/2018.
 */
@Module(includes = arrayOf(MainActivityContextModule::class))
class PicassoModule {

    @Provides
    fun picasso(@ActivityContext context: Context): Picasso{
        return Picasso.Builder(context).build()
    }
}