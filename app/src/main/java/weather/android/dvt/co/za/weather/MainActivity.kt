package weather.android.dvt.co.za.weather

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import timber.log.Timber
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherView
import weather.android.dvt.co.za.weather.WeatherInfo.di.Components.WeatherComponent
import weather.android.dvt.co.za.weather.WeatherInfo.di.Components.DaggerWeatherComponent
import weather.android.dvt.co.za.weather.WeatherInfo.di.Modules.MainActivityContextModule
import pub.devrel.easypermissions.EasyPermissions
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.widget.Toast
import pub.devrel.easypermissions.AfterPermissionGranted






class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    lateinit var weatherComponent: WeatherComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("%s activity was created.",MainActivity::class.java.simpleName)

        locationPermission()

        weatherComponent = DaggerWeatherComponent.builder()
                .mainActivityContextModule(MainActivityContextModule(this))
                .build()

    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    @AfterPermissionGranted(0)
    private fun locationPermission() {
        val perms = arrayOf<String>(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            supportFragmentManager.inTransaction {
                add(R.id.flContent,WeatherView())
            }

        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,getString(R.string.location_rationale),0,*perms)
          }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Timber.e("Location Permission denied")
        Toast.makeText(this,"Weather app requires location.", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Timber.d("Location Permission granted")
        supportFragmentManager.inTransaction {
            add(R.id.flContent,WeatherView())
        }
    }
}
