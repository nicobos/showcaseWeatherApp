package weather.android.dvt.co.za.weather.WeatherInfo.Implementation

import android.animation.Animator
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
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
 * Fragment that handles weather view events
 */
class WeatherView : Fragment(),IWeatherView {

    @Inject
    lateinit var weatherPresenter: WeatherPresenter

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var todaysDate: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_weather,container,false)

        weatherComponent.inject(this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("%s fragments view has been created.", WeatherView::class.java)

        setViewAlphaZero()

        /* Tell weatherPresenter to go and get the weather info */
        weatherPresenter.updateWeatherInfo()
        tvCurrentDay.text = todaysDate

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

        swiperefresh.setOnRefreshListener{
            Timber.d("Swipe refresh activated.")
            weatherPresenter.updateWeatherInfo()
        }
    }

    private fun showWeatherInfo(){
        Timber.d("Starting animation to show weather info */")
        /* Hide loading bar */
        llLoadingWeatherData.visibility = View.INVISIBLE
        /* Show weather data with alpha transition */
        animateViewAlphaToVisible(tvCurrentDay,0).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animateImage()
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        }
        )


    }

    fun animateImage(){

        animateViewAlphaToVisible(ivWeather,20).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animateDescription()
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })

    }

    fun animateDescription(){

        animateViewAlphaToVisible(tvDescription,40).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                animatemax()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })

    }

    fun animatemax(){
        animateViewAlphaToVisible(tvMaxTemp,60).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                animatemin()
            }

            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
    }

    fun animatemin(){
        animateViewAlphaToVisible(tvMinTemp,80).setListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                animatelocation()
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
    }

    fun animatelocation(){
        animateViewAlphaToVisible(tvLocation,100).setDuration(0)
    }

    fun animateViewAlphaToVisible(view: View,duration: Long): ViewPropertyAnimator{
        return view.animate().alpha(1.0f).setDuration(duration)
    }

    fun setViewAlphaZero(){
        tvLocation.alpha = 0f
        tvMinTemp.alpha = 0f
        tvMaxTemp.alpha = 0f
        tvDescription.alpha = 0f
        tvCurrentDay.alpha = 0f
        ivWeather.alpha = 0f
    }


}