package weather.android.dvt.co.za.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import timber.log.Timber
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherView
import weather.android.dvt.co.za.weather.WeatherInfo.di.Components.WeatherComponent
import weather.android.dvt.co.za.weather.WeatherInfo.di.Components.DaggerWeatherComponent
import weather.android.dvt.co.za.weather.WeatherInfo.di.Modules.MainActivityContextModule


class MainActivity : AppCompatActivity() {

    lateinit var weatherComponent: WeatherComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("%s activity was created.",MainActivity::class.java.simpleName)

        weatherComponent = DaggerWeatherComponent.builder()
                .mainActivityContextModule(MainActivityContextModule(this))
                .build()

        supportFragmentManager.inTransaction {
            add(R.id.flContent,WeatherView())
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
