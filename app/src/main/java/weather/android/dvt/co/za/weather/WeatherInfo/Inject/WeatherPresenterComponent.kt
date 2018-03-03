package weather.android.dvt.co.za.weather.WeatherInfo.Inject

import com.squareup.picasso.Picasso
import dagger.Component
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherPresenter


/**
 * Created by Wolf on 03/03/2018.
 */
@Component
interface RandomUserComponent {
    fun getWeatherPresenter(): WeatherPresenter
    fun getPicasso(): Picasso
}