package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_weather.*
import weather.android.dvt.co.za.weather.R
import weather.android.dvt.co.za.weather.WeatherInfo.DataModels.WeatherModel
import weather.android.dvt.co.za.weather.WeatherInfo.IWeatherView
import com.squareup.picasso.Callback
import timber.log.Timber
import weather.android.dvt.co.za.weather.WeatherInfo.di.weatherComponent
import javax.inject.Inject

/**
 * Created by Wolf on 03/03/2018.
 */
class WeatherView : Fragment(),IWeatherView {

    @Inject
    lateinit var weatherPresenter: WeatherPresenter

    @Inject
    lateinit var picasso: Picasso

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_weather,container,false)

        weatherComponent.inject(this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("%s fragments view has been created.", WeatherView::class.java)

        /* Tell weatherPresenter to go and get the weather info */
        weatherPresenter.updateWeatherInfo()

        weatherPresenter.weatherInfo.observe(this, Observer<WeatherModel>{
            Timber.i("Data received from weatherPresenter")
            tvMaxTemp.text = StringBuilder().append("max ").append(it?.maxTemp).append("\u2103").toString()
            tvMinTemp.text = StringBuilder().append("min ").append(it?.minTemp).append("\u2103").toString()
            tvLocation.text = it?.locationAdres
            tvDescription.text = it?.weatherDescription
            picasso.load(it?.imageResourceId!!)
                    .into(ivWeather,object: Callback {
                        override fun onSuccess() {
                            swiperefresh.isRefreshing = false
                            showWeatherInfo()
                            Timber.i("Picasso done loading image.")
                        }

                        override fun onError() {
                            Timber.e("Picasso failed to load image.")
                        }
                    })
        })

        swiperefresh.setOnRefreshListener( SwipeRefreshLayout.OnRefreshListener {
            Timber.d("Swipe refresh activated.")
            weatherPresenter.updateWeatherInfo()
        })
    }

    private fun showWeatherInfo(){
        Timber.d("Starting animation to show weather info */")
        /* Hide loading bar */
        llLoadingWeatherData.visibility = View.INVISIBLE
        /* Show weather data with alpha transition */
        clWeatherInfo.animate().alpha(1.0f)
    }
}