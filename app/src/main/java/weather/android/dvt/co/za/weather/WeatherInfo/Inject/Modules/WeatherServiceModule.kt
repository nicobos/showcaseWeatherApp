package weather.android.dvt.co.za.weather.WeatherInfo.Inject.Modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import weather.android.dvt.co.za.weather.WeatherInfo.IRetrofitWeatherService
import weather.android.dvt.co.za.weather.WeatherInfo.Inject.Scope.WeatherApplicationScope


/**
 * Created by Wolf on 03/03/2018.
 */
@Module
class WeatherServiceModule{
    val base_url = "http://api.openweathermap.org/data/2.5/"

    @Provides
    fun IRetroFitWeatherService(retrofit: Retrofit): IRetrofitWeatherService{
        return retrofit.create(IRetrofitWeatherService::class.java)
    }

    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit{
        return Retrofit.Builder()
                .baseUrl(base_url)
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
    fun rxJavaCallAdapterFactory(): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

}