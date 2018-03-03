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

/**
 * Created by Wolf on 03/03/2018.
 */
class WeatherView : Fragment(),IWeatherView {

    lateinit var weatherPresenter: WeatherPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_weather,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherPresenter = WeatherPresenter(activity)

        /* Tell weatherPresenter to go and get the weather info */
        weatherPresenter.updateWeatherInfo()

        weatherPresenter.weatherInfo.observe(this, Observer<WeatherModel>{
            tvMaxTemp.text = StringBuilder().append("max ").append(it?.maxTemp).append("\u2103").toString()
            tvMinTemp.text = StringBuilder().append("min ").append(it?.minTemp).append("\u2103").toString()
            tvLocation.text = it?.locationAdres
            Picasso.with(activity)
                    .load(it?.imageResourceId!!)
                    .into(ivWeather,object: Callback {
                        override fun onSuccess() {
                            swiperefresh.isRefreshing = false
                            showWeatherInfo()
                        }

                        override fun onError() {
                           /* Todo: show some error method */
                        }
                    })


        })

        swiperefresh.setOnRefreshListener( SwipeRefreshLayout.OnRefreshListener {
            weatherPresenter.updateWeatherInfo()
        })
    }

    private fun showWeatherInfo(){
        /* Hide loading bar */
        llLoadingWeatherData.visibility = View.INVISIBLE
        /* Show weather data with alpha transition */
        clWeatherInfo.animate().alpha(1.0f)
    }
}