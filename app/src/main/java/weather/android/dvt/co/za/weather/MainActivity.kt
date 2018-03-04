package weather.android.dvt.co.za.weather

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherView
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.*
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.MainActivityComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.DaggerMainActivityComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.DaggerWeatherServiceComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Components.WeatherServiceComponent
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.AppContextModule
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules.MainActivityContextModule
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var component: WeatherServiceComponent
    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //component.inject(this)
        component = DaggerWeatherServiceComponent.builder()
                .appContextModule(AppContextModule(application))
                .mainActivityContextModule(MainActivityContextModule(this))
                .build()
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(MainActivityContextModule(this))
                .weatherServiceComponent(component)
                .build()

        supportFragmentManager.inTransaction {
            add(R.id.flContent,WeatherView())
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
