package weather.android.dvt.co.za.weather.WeatherInfo.Inject

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Provides

/**
 * Created by Wolf on 03/03/2018.
 */
class PicassoModule {

    @Provides
    fun picasso(context: Context): Picasso{
        return Picasso.Builder(context).build()
    }
}