package weather.android.dvt.co.za.weather.WeatherInfo.di.Modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import weather.android.dvt.co.za.weather.R
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.Repositories.WeatherDataRepository
import weather.android.dvt.co.za.weather.WeatherInfo.Implementation.WeatherDataRepositoryImpl
import weather.android.dvt.co.za.weather.WeatherInfo.di.Scope.ActivityContext

/**
 * Created by Wolf on 04/03/2018.
 */
@Module(includes = arrayOf(MainActivityContextModule::class))
class WeatherDataRepositoryModel {

    @Provides
    fun providesWeatherDataRepository(weatherService: IRetrofitWeatherService,@ActivityContext context: Context): WeatherDataRepository {
        return WeatherDataRepositoryImpl(weatherService,context)
    }

    @Provides
    fun IRetroFitWeatherService(retrofit: Retrofit): IRetrofitWeatherService{
        return retrofit.create(IRetrofitWeatherService::class.java)
    }

    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, @ActivityContext context: Context) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.open_weather_service_url))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun rxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }


}